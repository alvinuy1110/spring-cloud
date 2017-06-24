package com.myproject.cloud;

import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Profile("bus")
@Configuration
@Import(RabbitAutoConfiguration.class)
public class ConfigMonitorConfiguration {

}