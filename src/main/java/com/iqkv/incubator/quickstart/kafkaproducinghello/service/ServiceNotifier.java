package com.iqkv.incubator.quickstart.kafkaproducinghello.service;

import com.iqkv.incubator.quickstart.kafkaproducinghello.model.Greeting;
import com.iqkv.incubator.quickstart.kafkaproducinghello.producer.GreetingMessageProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceNotifier {

  private final GreetingMessageProducer messageProducer;

  @Scheduled(fixedDelay = 10000)
  public void scheduledCall() {
    messageProducer.send(new Greeting("Hello, World!"));
  }
}
