package com.example.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EventConsumers {
  private static final Logger log = LoggerFactory.getLogger(EventConsumers.class);

  @KafkaListener(topics = "assignment.due")
  public void assignmentDue(String payload) { log.info("NOTIFY: assignment due -> {}", payload); }

  @KafkaListener(topics = "assignment.submitted")
  public void assignmentSubmitted(String payload) { log.info("NOTIFY: assignment submitted -> {}", payload); }

  @KafkaListener(topics = "class.created")
  public void classCreated(String payload) { log.info("NOTIFY: class created -> {}", payload); }

  @KafkaListener(topics = "user.enrolled")
  public void userEnrolled(String payload) { log.info("NOTIFY: user enrolled -> {}", payload); }
}
