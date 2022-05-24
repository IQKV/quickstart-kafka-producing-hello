package org.ujar.basics.kafka.producing.hello.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableConfigurationProperties({KafkaTopicsProperties.class})
@EnableScheduling
public class ApplicationConfig {
}
