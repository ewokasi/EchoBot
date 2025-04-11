package org.example.echobot.controller;

import org.example.echobot.service.VkApiService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class VkCallbackControllerTest {

    @Autowired
    private MockMvc mockMvc; // Для тестирования контроллеров

    @MockBean
    private VkApiService vkApiService; // Мокируем сервис

    @Value("${vk.code}")
    private String confirmationCode;

    @Test
    public void testHandleCallback_Confirmation() throws Exception {
        // Мокируем возвращаемое значение для внешнего API (RestTemplate)
        mockMvc.perform(post("/api/vk/callback")
                .contentType("application/json")
                .content("{\"type\": \"confirmation\", \"object\": {}}"))
                .andExpect(status().isOk())
                .andExpect(content().string(confirmationCode)); // Проверяем, что вернулся код подтверждения
    }

    @Test
    public void testHandleCallback_MessageNew() throws Exception {
        when(vkApiService.sendMessage(12345, "Test message")).thenReturn("ok");

        mockMvc.perform(post("/api/vk/callback")
                        .contentType("application/json")
                        .content("{\"type\": \"message_new\", \"object\": { \"message\": { \"peer_id\": 12345, \"text\": \"Test message\" } } }"))
                .andExpect(status().isOk())
                .andExpect(content().string("ok")); // Проверяем, что сервис вернул "ok"
    }
}
