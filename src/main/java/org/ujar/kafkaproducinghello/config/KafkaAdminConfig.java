package org.ujar.kafkaproducinghello.config;

import static org.ujar.kafkaproducinghello.config.Constants.TOPIC_DEFINITION_HELLO_WORLD;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.ujar.boot.kafka.config.KafkaTopicDefinitionProperties;

@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(value = "ujar.kafka.admin.create-topics", havingValue = "true")
class KafkaAdminConfig {
  private final KafkaTopicDefinitionProperties topicDefinitions;

  @Bean
  NewTopic helloWorldKafkaTopic() {
    final var definition = topicDefinitions.get(TOPIC_DEFINITION_HELLO_WORLD);
    return TopicBuilder
        .name(definition.getName())
        .partitions(definition.getPartitions())
        .config(TopicConfig.RETENTION_MS_CONFIG, "" + definition.getRetention().toMillis())
        .build();
  }
}
