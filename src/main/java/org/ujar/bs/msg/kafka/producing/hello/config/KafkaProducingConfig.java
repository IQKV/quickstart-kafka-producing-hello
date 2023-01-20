package org.ujar.bs.msg.kafka.producing.hello.config;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.ujar.boot.starter.kafka.config.BaseKafkaProducingConfig;
import org.ujar.bs.msg.kafka.producing.hello.model.Greeting;

@Configuration
class KafkaProducingConfig extends BaseKafkaProducingConfig {

  @Bean
  ProducerFactory<String, Greeting> greetingMessageProducerFactory(KafkaProperties kafkaProperties) {
    return producerFactory(Greeting.class, kafkaProperties);
  }

  @Bean
  KafkaTemplate<String, Greeting> greetingMessageKafkaTemplate(
      ProducerFactory<String, Greeting> greetingMessageProducerFactory) {
    return kafkaTemplate(greetingMessageProducerFactory);
  }
}
