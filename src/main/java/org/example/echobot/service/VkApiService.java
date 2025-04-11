package org.example.echobot.service;

import org.example.echobot.config.VkConfig;
import org.example.echobot.dto.VkMessage;
import org.example.echobot.dto.VkMessageResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class VkApiService {
    private final RestTemplate restTemplate;

    private String token;
    private String version;
    private String apiUrl;

    public VkApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.token = VkConfig.getToken();
        this.version = VkConfig.getVersion();
        this.apiUrl = VkConfig.getUrl();
    }
    @Retryable(maxAttempts = 3,
               backoff = @Backoff(delay = 1000),
               retryFor = {RestClientException.class}
              )
    public String sendMessage(Integer peerId, String message) {
        String url = String.format("%smessages.send?peer_id=%d&message=%s&access_token=%s&v=%s&random_id=%d",
                apiUrl, peerId, message, token, version, (int) (Math.random() * Integer.MAX_VALUE));

        restTemplate.getForObject(url, VkMessageResponse.class);
        return "ok";
    }
}