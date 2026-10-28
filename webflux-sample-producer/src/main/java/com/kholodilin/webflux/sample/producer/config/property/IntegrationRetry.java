package com.kholodilin.webflux.sample.producer.config.property;

import lombok.Data;

/**
 * Retry settings for failed calls.
 * Not wired into the WebClient chain yet — values are already loaded from config.
 */
@Data
public class IntegrationRetry {
    /** How many times to retry after a failure. */
    private int maxAttempts;
    /** Minimum backoff before a retry, milliseconds. */
    private long minBackoffMs;
}
