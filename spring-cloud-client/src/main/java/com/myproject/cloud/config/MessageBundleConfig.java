package com.myproject.cloud.config;

import com.myproject.cloud.domain.Features;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * This approach works but you have to put @RefreshScope at the config and the Bean that uses it
 */

@RefreshScope
@Configuration
public class MessageBundleConfig {

    @Autowired
    private Environment env;

    @RefreshScope
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();

        // using local file
        //String basename = "file:/home/user/git/spring-cloud/spring-cloud-server/src/main/resources/config/messages";

        // Using cloud config
        String basename = "http://localhost:8888/foo/default/master/messages";

        reloadableResourceBundleMessageSource.setBasename(basename);
        reloadableResourceBundleMessageSource.setCacheSeconds(10);


        return reloadableResourceBundleMessageSource;

    }

}
