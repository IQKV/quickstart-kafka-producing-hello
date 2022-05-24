package org.ujar.basics.kafka.producing.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaProducingHelloApplication {
  public static void main(String[] args) {
    SpringApplication.run(KafkaProducingHelloApplication.class, args);
  }
}
