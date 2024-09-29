package dev.knowhowto.kafkaproducinghello.config;

import java.util.HashMap;

import com.iqkv.boot.kafka.config.BaseKafkaConsumingConfig;
import dev.knowhowto.kafkaproducinghello.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
class KafkaConsumingTestConfig extends BaseKafkaConsumingConfig {

  @Autowired
  KafkaConsumingTestConfig(LocalValidatorFactoryBean validator) {
    super(validator);
  }

  @Bean
  ConsumerFactory<String, Greeting> consumeGreetingConsumerFactory(KafkaProperties kafkaProperties, SslBundles sslBundles) {
    return consumerFactory(Greeting.class, kafkaProperties, sslBundles);
  }

  @Bean
  ConcurrentKafkaListenerContainerFactory<String, Greeting> consumeGreetingKafkaListenerContainerFactory(
      ConsumerFactory<String, Greeting> consumeGreetingConsumerFactory,
      @Value("${iqkv.kafka.consumer.threads:2}") int threads, DefaultErrorHandler errorHandler) {
    return containerFactory(consumeGreetingConsumerFactory, threads, errorHandler);
  }

  @Bean
  KafkaOperations<Object, Object> errorHandlingKafkaTemplate() {
    return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(new HashMap<>()));
  }
}
