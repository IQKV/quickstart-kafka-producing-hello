package org.ujar.bs.msg.kafka.producing.hello.config;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.ujar.bs.msg.kafka.producing.hello.model.Greeting;
import org.ujar.boot.starter.kafka.config.BaseKafkaConsumingConfig;

@Configuration
class KafkaConsumingTestConfig extends BaseKafkaConsumingConfig {

  @Autowired
  KafkaConsumingTestConfig(LocalValidatorFactoryBean validator) {
    super(validator);
  }

  @Bean
  ConsumerFactory<String, Greeting> consumeGreetingConsumerFactory(KafkaProperties kafkaProperties) {
    return consumerFactory(Greeting.class, kafkaProperties);
  }

  @Bean
  ConcurrentKafkaListenerContainerFactory<String, Greeting> consumeGreetingKafkaListenerContainerFactory(
      ConsumerFactory<String, Greeting> consumeGreetingConsumerFactory,
      @Value("${ujar.kafka.consumer.threads:2}") int threads, DefaultErrorHandler errorHandler) {
    return containerFactory(consumeGreetingConsumerFactory, threads, errorHandler);
  }

  @Bean
  KafkaOperations<Object, Object> errorHandlingKafkaTemplate() {
    return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(new HashMap<>()));
  }
}
