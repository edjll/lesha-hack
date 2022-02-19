package ru.frankwoods.data.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import ru.frankwoods.data.config.constants.KafkaConfigConstants;
import ru.frankwoods.data.entity.CandleHolder;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value(KafkaConfigConstants.VALUE_BOOTSTRAP_SERVERS)
    private String bootstrapServer;

    @Value(KafkaConfigConstants.VALUE_SECURITY_PROTOCOL)
    private String securityProtocol;

    @Value(KafkaConfigConstants.VALUE_SASL_JAAS_CONFIG)
    private String saslJaasConfig;

    @Value(KafkaConfigConstants.VALUE_SASL_MECHANISM)
    private String saslMechanism;

    @Value(KafkaConfigConstants.VALUE_SSL_ENDPOINT_IDENTIFICATION_ALGORITHM)
    private String sslEndpointIdentificationAlgorithm;

    @Value(KafkaConfigConstants.VALUE_ALLOW_AUTO_CREATE_TOPICS)
    private Boolean allowAutoCreateTopics;

    @Value(KafkaConfigConstants.VALUE_AUTO_OFFSET_RESET)
    private String autoOffsetReset;

    @Bean
    public ConsumerFactory<String, CandleHolder> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(KafkaConfigConstants.SECURITY_PROTOCOL, securityProtocol);
        props.put(KafkaConfigConstants.SASL_JAAS_CONFIG, saslJaasConfig);
        props.put(KafkaConfigConstants.SASL_MECHANISM, saslMechanism);
        props.put(KafkaConfigConstants.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM, sslEndpointIdentificationAlgorithm);
        props.put(KafkaConfigConstants.ALLOW_AUTO_CREATE_TOPICS, allowAutoCreateTopics);
        props.put(KafkaConfigConstants.AUTO_OFFSET_RESET, autoOffsetReset);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(CandleHolder.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CandleHolder> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, CandleHolder> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}