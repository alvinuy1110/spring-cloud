package com.myproject.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class ConfigClient {

    public static void main(String[] arguments) {
        SpringApplication.run(ConfigClient.class, arguments);
    }
}