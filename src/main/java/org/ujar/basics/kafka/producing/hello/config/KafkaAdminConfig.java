package org.ujar.basics.kafka.producing.hello.config;

import static org.ujar.basics.kafka.producing.hello.config.Constants.TOPIC_DEFINITION_HELLO_WORLD;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.ujar.boot.starter.kafka.config.KafkaTopicDefinitionProperties;

@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(value = "ujar.kafka.create-topics-on-startup", havingValue = "true")
class KafkaAdminConfig {
  private final KafkaTopicDefinitionProperties topicDefinitions;

  @Bean
  NewTopic helloWorldKafkaTopic() {
    var definition = topicDefinitions.get(TOPIC_DEFINITION_HELLO_WORLD);
    return TopicBuilder
        .name(definition.name())
        .partitions(definition.partitions())
        .config(TopicConfig.RETENTION_MS_CONFIG, "" + definition.retention().toMillis())
        .build();
  }
}
