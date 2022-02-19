package ru.frankwoods.data.config.constants;

import org.apache.kafka.clients.consumer.ConsumerConfig;

public interface KafkaConfigConstants {

    String SECURITY_PROTOCOL = "security.protocol";
    String SASL_JAAS_CONFIG = "sasl.jaas.config";
    String SASL_MECHANISM = "sasl.mechanism";
    String SSL_ENDPOINT_IDENTIFICATION_ALGORITHM = "ssl.endpoint.identification.algorithm";
    String ALLOW_AUTO_CREATE_TOPICS = "allow.auto.create.topics";
    String AUTO_OFFSET_RESET = "auto.offset.reset";

    String VALUE_PREFIX = "${spring.kafka.";
    String VALUE_SUFFIX = "}";
    String VALUE_SECURITY_PROTOCOL = VALUE_PREFIX + SECURITY_PROTOCOL + VALUE_SUFFIX;
    String VALUE_SASL_JAAS_CONFIG = VALUE_PREFIX + SASL_JAAS_CONFIG + VALUE_SUFFIX;
    String VALUE_SASL_MECHANISM = VALUE_PREFIX + SASL_MECHANISM + VALUE_SUFFIX;
    String VALUE_SSL_ENDPOINT_IDENTIFICATION_ALGORITHM = VALUE_PREFIX + SSL_ENDPOINT_IDENTIFICATION_ALGORITHM + VALUE_SUFFIX;
    String VALUE_ALLOW_AUTO_CREATE_TOPICS = VALUE_PREFIX + ALLOW_AUTO_CREATE_TOPICS + VALUE_SUFFIX;
    String VALUE_AUTO_OFFSET_RESET = VALUE_PREFIX + AUTO_OFFSET_RESET + VALUE_SUFFIX;
    String VALUE_BOOTSTRAP_SERVERS = VALUE_PREFIX + ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG + VALUE_SUFFIX;

    String TOPIC = "rht.candles";
    String GROUP_ID = "hackathon-Meta 2.0";
}