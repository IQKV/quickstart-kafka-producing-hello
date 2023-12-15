package dev.knowhowto.kafkaproducinghello.producer;

import static dev.knowhowto.kafkaproducinghello.config.Constants.TOPIC_DEFINITION_HELLO_WORLD;

import java.util.UUID;

import dev.knowhowto.kafkaproducinghello.model.Greeting;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iqkv.boot.kafka.config.KafkaTopicDefinitionProperties;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class GreetingMessageProducer {
  private final KafkaTemplate<String, Greeting> kafkaTemplate;
  private final KafkaTopicDefinitionProperties topicDefinitions;

  /**
   * Send message to Kafka broker with avoiding transaction-aware configuration environment
   */
  public void send(Greeting message) {
    final var key = UUID.randomUUID().toString();
    log.info("( {} ) Send message, key: {}, value: {}", Thread.currentThread().getName(), key, message);
    kafkaTemplate.executeInTransaction(op -> op.send(topicDefinitions.get(TOPIC_DEFINITION_HELLO_WORLD).getName(),
        key,
        message)
    );
  }
}
