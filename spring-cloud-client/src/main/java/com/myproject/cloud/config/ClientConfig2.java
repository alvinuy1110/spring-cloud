package com.myproject.cloud.config;

import com.myproject.cloud.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This approach works. you have to put @RefreshScope at the bean.  And the property is within the scope of the Bean as well
 *
 * Note @Configuration was commented out to not collide with ClientConfig
 */


//@Configuration
public class ClientConfig2 {

    @Bean
    @RefreshScope
    public User user(@Value("${user.name}") String userName, @Value("${user.email}") String userEmail) {
        User user = new User();
        user.setName(userName);
        user.setEmail(userEmail);
        return user;
    }

}
