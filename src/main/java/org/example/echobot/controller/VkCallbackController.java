package org.example.echobot.controller;
import org.example.echobot.dto.VkCallbackRequest;
import org.example.echobot.dto.VkEventType;
import org.example.echobot.service.VkApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.example.echobot.config.VkConfig;

@RestController
@RequestMapping("/api/vk")
public class VkCallbackController {
    private static final Logger logger = LoggerFactory.getLogger(VkCallbackController.class);
    private final VkApiService vkApiService;

    private final String confirmationCode ;


    public VkCallbackController(VkApiService vkApiService) {
        this.vkApiService = vkApiService;
        this.confirmationCode = VkConfig.getCode();
    }

    @PostMapping(
            value = "/callback",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE
    )


    public String handleCallback(@RequestBody VkCallbackRequest request) {
        try {
            //все события попадают сюда
            if (request == null) {
                logger.error("received null request");
                return "ok";//чтобы вк не повторял запрос, так как считает только ок за успех
            }

            VkEventType eventType = VkEventType.fromString(request.getType());
            switch (eventType) {
                case UNKNOWN:
                    logger.warn("Unknown request, type {}",request.getType() );

                    return "ok";//чтобы вк не повторял запрос, так как считает только ок за успех


                case CONFIRMATION:
                    logger.info("received confirmation request");
                    return confirmationCode; //По докам вк надо вернуть plaintext


                case MESSAGE_NEW:
                    String text = request.getObject().getMessage().getText();
                    Integer peerId = request.getObject().getMessage().getPeerId();
                    if (text != null && peerId != null && !text.isEmpty()) {
                        logger.info("Sending message to peerId={}: {}", peerId, text);
                        vkApiService.sendMessage(peerId, "Вы сказали: " + text);
                        return "ok";
                    } else {
                        logger.warn("Message text or peerId is null");
                        return "ok";//чтобы вк не повторял запрос, так как считает только ок за успех
                    }
            }

        } catch (Exception e) {
            logger.warn("Произошла ошибка при обработке входящего запроса:{}", e.getMessage());
            throw new RuntimeException(e);
        }
        return "ok";
    }
}