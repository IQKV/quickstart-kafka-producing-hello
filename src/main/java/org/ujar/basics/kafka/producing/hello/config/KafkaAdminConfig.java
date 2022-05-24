package org.ujar.basics.kafka.producing.hello.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@RequiredArgsConstructor
class KafkaAdminConfig {
  private final KafkaTopicsProperties topics;

  @Bean
  public NewTopic helloWorldKafkaTopic() {
    var definition = topics.get(KafkaTopicsProperties.HELLO_WORLD);
    return TopicBuilder
        .name(definition.name())
        .partitions(definition.partitions())
        .config(TopicConfig.RETENTION_MS_CONFIG, "" + definition.retention().toMillis())
        .build();
  }
}
