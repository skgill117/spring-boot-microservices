package com.example.service;

import com.example.configs.KafkaTopicConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @Autowired
    private KafkaTopicConfig kafkaTopicConfig;

    // Dynamic topic assignment using KafkaListener
    @KafkaListener(topics = "#{kafkaTopicConfig.getTopics()['my-topic']}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
        System.out.println("Received message: " + record.value() + " from topic: " + record.topic());
        System.out.println("Thread ID: " + Thread.currentThread().getId() + " - Consumed message: " + record.value());

        // Manually acknowledge the message
        acknowledgment.acknowledge();
    }


    /*
    * Explanation:
      @KafkaListener: The listener will listen to the specified topic (my-topic).
      *Since the concurrency is set to 3, there will be 3 threads listening to the topic,
      * and each will process messages from different partitions.
      Multi-threading: You can see the thread ID printed out,
      showing that multiple threads are processing the records concurrently.
    * */

    /*
    * Scaling Up Multi-threaded Kafka Consumers
        If you want to increase the number of threads,
        * simply increase the concurrency value in the kafkaListenerContainerFactory().
        Ensure that your Kafka topic has enough partitions to distribute the load.
        * If your topic has fewer partitions than the concurrency value,
        * some threads will not be assigned to partitions and will remain idle.
    * */
}

