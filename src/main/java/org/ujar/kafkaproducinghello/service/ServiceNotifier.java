package org.ujar.kafkaproducinghello.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.ujar.kafkaproducinghello.model.Greeting;
import org.ujar.kafkaproducinghello.producer.GreetingMessageProducer;

@Service
@RequiredArgsConstructor
public class ServiceNotifier {

  private final GreetingMessageProducer messageProducer;

  @Scheduled(fixedDelay = 10000)
  public void scheduledCall() {
    messageProducer.send(new Greeting("Hello, World!"));
  }
}
