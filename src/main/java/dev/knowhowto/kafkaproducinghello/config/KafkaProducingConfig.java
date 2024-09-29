package dev.knowhowto.kafkaproducinghello.config;

import com.iqkv.boot.kafka.config.BaseKafkaProducingConfig;
import dev.knowhowto.kafkaproducinghello.model.Greeting;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
class KafkaProducingConfig extends BaseKafkaProducingConfig {

  @Bean
  ProducerFactory<String, Greeting> greetingMessageProducerFactory(KafkaProperties kafkaProperties, SslBundles sslBundles) {
    return producerFactory(Greeting.class, kafkaProperties, sslBundles);
  }

  @Bean
  KafkaTemplate<String, Greeting> greetingMessageKafkaTemplate(
      ProducerFactory<String, Greeting> greetingMessageProducerFactory) {
    return kafkaTemplate(greetingMessageProducerFactory);
  }
}
