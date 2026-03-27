package me.andre.consumer;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@SpringBootApplication
@EnableScheduling
@RequiredArgsConstructor
public class ConsumerApplication {



  public static void main(String[] args) {
    SpringApplication.run(ConsumerApplication.class, args);
  }

  private final BlockingQueue<String> queue = new LinkedBlockingQueue<>();
  private final int messagesPerSecond = 2;

  @KafkaListener(topics = "test-topic", groupId = "consumer-group")
  public void listen(String message) {
    queue.offer(message);
  }

  @Scheduled(fixedRate = 1000)
  public void processMessages() {
    for (int i = 0; i < messagesPerSecond; i++) {
      String msg = queue.poll();
      if (msg == null) break;
      log.info("Consumed {}", msg);
    }
  }
}