package com.example.configs;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "${spring.kafka.bootstrap-servers}");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "${spring.kafka.consumer.group-id}");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(3);  // Number of concurrent threads, this should be equal to number of partitions
        return factory;
    }


    /*
    To enable multi-threaded processing in a Kafka consumer within a Spring Boot application,
      you can utilize Kafka’s ability to partition topics and configure
      the consumer to consume messages in parallel using multiple threads.
      This approach allows you to scale the message processing capacity of your consumer.

  Here are the steps to configure Kafka consumers with multi-threading support in a Spring Boot application:

   1. Ensure Your Kafka Topic Has Partitions
    Kafka partitions are key to enabling parallelism in Kafka consumers.

  Each partition can be consumed by one thread in a consumer group.
   If you don’t have partitions, a single consumer will be processing all the messages
     in a linear fashion, regardless of how many threads you create.

     Configure Kafka Consumer with Multi-threading
           In Spring Boot, you can configure multi-threading by setting the concurrency parameter
            in the ConcurrentKafkaListenerContainerFactory.
          This setting allows Spring to use multiple consumers (threads)
            to process messages from different partitions of the same topic.
    * */
}

