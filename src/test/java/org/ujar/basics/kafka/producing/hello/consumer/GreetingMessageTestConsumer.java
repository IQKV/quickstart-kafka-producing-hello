package org.ujar.basics.kafka.producing.hello.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.ujar.basics.kafka.producing.hello.model.Greeting;

@Component
@RequiredArgsConstructor
@Slf4j
public class GreetingMessageTestConsumer {

  @KafkaListener(containerFactory = "consumeGreetingKafkaListenerContainerFactory",
                 topics = "${ujar.kafka.topics.hello-world.name}",
                 groupId = "${spring.kafka.consumer.group-id}")
  public void consume(ConsumerRecord<String, Greeting> consumerRecord) {
    log.info("( {} ) Received message, key: {}, value: {}",
        Thread.currentThread().getName(), consumerRecord.key(), consumerRecord.value());
  }
}
