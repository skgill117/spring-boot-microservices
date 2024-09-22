package com.example.configs;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.config.KafkaStreamsConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableKafkaStreams
public class KafkaStreamConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.streams.application-id}")
    private String applicationId;

    @Value("${spring.kafka.streams.commit-interval-ms}")
    private Integer commitIntervalMs;

    @Value("${spring.kafka.topics.input-topic}")
    private String inputTopic;

    @Value("${spring.kafka.topics.output-topic}")
    private String outputTopic;

    @Bean(name = "kafkaStreamsConfig")
    public KafkaStreamsConfiguration kafkaStreamsConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("application.id", applicationId);
        props.put("commit.interval.ms", commitIntervalMs);
        props.put("default.key.serde", Serdes.String().getClass().getName());
        props.put("default.value.serde", Serdes.String().getClass().getName());
        return new KafkaStreamsConfiguration(props);
    }

    @Bean
    public KStream<String, String> kStream(StreamsBuilder builder) {
        // Stream data from the input topic
        KStream<String, String> stream = builder.stream(inputTopic);

        // Perform word count processing on the stream
        KTable<String, Long> wordCounts = stream
                .flatMapValues(value -> List.of(value.toLowerCase().split("\\W+")))
                .groupBy((key, word) -> word)
                .count();

        // Write the result to the output topic
        wordCounts.toStream().to(outputTopic, Produced.with(Serdes.String(), Serdes.Long()));

        return stream;
    }
}


