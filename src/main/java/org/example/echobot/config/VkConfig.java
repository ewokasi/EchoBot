package org.example.echobot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

@Configuration
@Validated
@ConfigurationProperties(prefix = "vk")
public class VkConfig {

    private static String token;
    private static String version;
    private static String url;
    private static String code;

    // Геттеры и сеттеры
    public static String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public static String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
