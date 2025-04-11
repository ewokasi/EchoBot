package org.example.echobot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VkCallbackObject {
    private VkMessage message;  // Объект, который будет содержать данные о сообщении
    @JsonProperty("client_info")
    private VkClientInfo clientInfo;  // Объект с информацией о клиенте
}
