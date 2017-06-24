package com.myproject.cloud.config;

import com.myproject.cloud.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This approach works but you have to put @RefreshScope at the config and the Bean that uses it
 */

@RefreshScope
@Configuration
public class ClientConfig {
    @Value("${user.name}")
    private String userName;

    @Value("${user.email}")
    private String userEmail;

    @RefreshScope
    @Bean
    public User user() {
        User user = new User();
        user.setName(userName);
        user.setEmail(userEmail);
        return user;
    }


}
