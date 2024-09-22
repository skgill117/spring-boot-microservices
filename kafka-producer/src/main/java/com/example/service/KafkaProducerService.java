package com.example.service;

import com.example.configs.KafkaTopicConfig;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTopicConfig kafkaTopicConfig;

    public void sendMessage(String topicKey, String message) {
        // Get the topic name dynamically from the config
        String topic = kafkaTopicConfig.getTopicName(topicKey);

        // Send the message and handle CompletableFuture
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                // Success
                RecordMetadata metadata = result.getRecordMetadata();
                System.out.println("Message sent successfully to topic " + metadata.topic() +
                        " with partition " + metadata.partition() +
                        " and offset " + metadata.offset());
            } else {
                // Failure
                System.err.println("Failed to send message to topic " + topic + " due to: " + ex.getMessage());
            }
        });
    }
}
