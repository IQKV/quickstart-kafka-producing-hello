/*
 * Copyright 2025 IQKV Foundation Team.
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

package com.iqkv.quickstart.kafkaproducinghello.consumer;

import java.util.concurrent.CountDownLatch;

import com.iqkv.boot.kafka.exception.ConsumerRecordProcessingException;
import com.iqkv.quickstart.kafkaproducinghello.model.Greeting;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@Getter
public class GreetingMessageConsumer {

  private CountDownLatch latch = new CountDownLatch(1);
  private String payload;

  @KafkaListener(containerFactory = "consumeGreetingKafkaListenerContainerFactory",
                 topics = "${expertness.kafka.topics.hello-world.name}",
                 groupId = "${spring.kafka.consumer.group-id}")
  public void consume(ConsumerRecord<String, Greeting> consumerRecord) {
    try {
      log.info("( {} ) Received message, key: {}, value: {}",
          Thread.currentThread().getName(), consumerRecord.key(), consumerRecord.value());
      payload = consumerRecord.value().toString();
      latch.countDown();
    } catch (Exception e) {
      throw new ConsumerRecordProcessingException("Error processing message data.", e);
    }
  }

  public void resetLatch() {
    latch = new CountDownLatch(1);
  }
}

