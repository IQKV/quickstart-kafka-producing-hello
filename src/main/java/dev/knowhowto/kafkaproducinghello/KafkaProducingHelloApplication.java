package dev.knowhowto.kafkaproducinghello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;

@SpringBootApplication
public class KafkaProducingHelloApplication {
  public static void main(String[] args) {
    SpringApplication springApplication = new SpringApplication(KafkaProducingHelloApplication.class);
    springApplication.setApplicationStartup(new BufferingApplicationStartup(2048));
    springApplication.run(args);
  }
}
