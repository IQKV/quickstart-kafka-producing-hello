package org.ujar.basics.kafka.producing.hello.producer;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.ujar.basics.kafka.producing.hello.config.KafkaTopicsProperties;
import org.ujar.basics.kafka.producing.hello.model.Greeting;

@Component
@Slf4j
@RequiredArgsConstructor
@SuppressFBWarnings("EI_EXPOSE_REP2")
public class GreetingMessageProducer {
  private final KafkaTemplate<String, Greeting> kafkaTemplate;
  private final KafkaTopicsProperties topics;

  /**
   * Send message to Kafka broker with avoiding transaction-aware configuration environment
   */
  public void send(Greeting message) {
    var key = UUID.randomUUID().toString();
    log.info("( {} ) Send message, key: {}, value: {}", Thread.currentThread().getName(), key, message);
    kafkaTemplate.executeInTransaction(t -> t.send(
        topics.get(KafkaTopicsProperties.HELLO_WORLD).name(),
        key,
        message)
    );
  }
}
