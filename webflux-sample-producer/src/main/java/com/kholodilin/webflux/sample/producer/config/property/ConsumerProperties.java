package com.kholodilin.webflux.sample.producer.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Integration settings for the consumer service.
 * Prefix in application.properties: {@code consumer-connection.*}
 */
@Data
@ConfigurationProperties(prefix = "consumer-connection")
public class ConsumerProperties {
    /** Consumer HTTP URL and timeouts. */
    private IntegrationConnection connection;
    /** Retry settings (prepared for retries on call failures; not wired into WebClient yet). */
    private IntegrationRetry retry;
}
