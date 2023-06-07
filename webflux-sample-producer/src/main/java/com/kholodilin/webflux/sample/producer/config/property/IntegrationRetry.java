package com.kholodilin.webflux.sample.producer.config.property;

import lombok.Data;

@Data
public class IntegrationRetry {
    private int maxAttempts;
    private long minBackoffMs;
}
