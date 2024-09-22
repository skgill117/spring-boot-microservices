package com.example.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "spring.kafka")
public class KafkaTopicConfig {

    private Map<String, String> topics;

    public Map<String, String> getTopics() {
        return topics;
    }

    public void setTopics(Map<String, String> topics) {
        this.topics = topics;
    }

    public String getTopicName(String key) {
        return topics.getOrDefault(key, "default_topic");
    }
}

