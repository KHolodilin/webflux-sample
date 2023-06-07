package com.kholodilin.webflux.sample.producer.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "consumer-connection")
public class ConsumerProperties {
    private IntegrationConnection connection;
    private IntegrationRetry retry;
}
