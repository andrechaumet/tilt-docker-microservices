package me.andre.producer;

import java.util.Random;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@EnableScheduling
@SpringBootApplication
@RequiredArgsConstructor
public class ProducerApplication {

  public static void main(String[] args) {
    SpringApplication.run(ProducerApplication.class, args);
  }

  private final KafkaTemplate<String, String> kafkaTemplate;
  private final Random random = new Random();

  @Bean
  public NewTopic topic() {
    return new NewTopic("test-topic", 1, (short) 1);
  }

  @Scheduled(fixedRate = 1000)
  public void sendRandomMessage() {
    String msg = "msg-" + random.nextInt(1000);
    kafkaTemplate.send("test-topic", msg);
    log.info("Sent message: {}", msg);
  }
}