package com.example.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicsConfig {
    @Bean
    public NewTopic classCreatedTopic() { return TopicBuilder.name("class.created").build(); }
    @Bean
    public NewTopic assignmentDueTopic() { return TopicBuilder.name("assignment.due").build(); }
    @Bean
    public NewTopic assignmentSubmittedTopic() { return TopicBuilder.name("assignment.submitted").build(); }
    @Bean
    public NewTopic userEnrolledTopic() { return TopicBuilder.name("user.enrolled").build(); }
}
