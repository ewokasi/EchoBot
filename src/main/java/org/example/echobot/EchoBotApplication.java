package org.example.echobot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;


@SpringBootApplication
@EnableRetry
public class EchoBotApplication {
    public static void main(String[] args) {
        SpringApplication.run(EchoBotApplication.class, args);
    }
}