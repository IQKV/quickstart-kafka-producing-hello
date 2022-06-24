package org.ujar.basics.kafka.producing.hello.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.ujar.basics.kafka.producing.hello.model.Greeting;

@Configuration
class KafkaConfig {
  @Bean
  ProducerFactory<String, Greeting> greetingMessageProducerFactory(KafkaProperties kafkaProperties) {
    try (var serde = new JsonSerde<>(Greeting.class, new ObjectMapper())) {
      var producerProperties = kafkaProperties.getProducer().buildProperties();
      var producerFactory = new DefaultKafkaProducerFactory<>(producerProperties,
          new StringSerializer(),
          serde.serializer());
      producerFactory.setTransactionIdPrefix(getTransactionPrefix());
      return producerFactory;
    }
  }

  @Bean
  KafkaTemplate<String, Greeting> greetingMessageKafkaTemplate(
      ProducerFactory<String, Greeting> greetingMessageProducerFactory) {
    return new KafkaTemplate<>(greetingMessageProducerFactory);
  }

  private String getTransactionPrefix() {
    return "tx-" + UUID.randomUUID() + "-";
  }
}
