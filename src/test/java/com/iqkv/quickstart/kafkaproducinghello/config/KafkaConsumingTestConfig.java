/*
 * Copyright 2025 IQKV Team.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.iqkv.quickstart.kafkaproducinghello.config;

import java.util.HashMap;

import expert.uses.boot.kafka.config.BaseKafkaConsumingConfig;

import com.iqkv.quickstart.kafkaproducinghello.model.Greeting;
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
      @Value("${expertness.kafka.consumer.threads:2}") int threads, DefaultErrorHandler errorHandler) {
    return containerFactory(consumeGreetingConsumerFactory, threads, errorHandler);
  }

  @Bean
  KafkaOperations<Object, Object> errorHandlingKafkaTemplate() {
    return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(new HashMap<>()));
  }
}
