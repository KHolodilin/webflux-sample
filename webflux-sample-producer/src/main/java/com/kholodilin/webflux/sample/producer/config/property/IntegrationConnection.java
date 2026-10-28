package com.kholodilin.webflux.sample.producer.config.property;

import lombok.Data;

/** HTTP connection settings for an external service (here — the consumer). */
@Data
public class IntegrationConnection {
    /** Base URL, e.g. {@code http://localhost:8881}. */
    private String url;
    /** Optional endpoint name (for logs / extensions). */
    private String endPointName;
    /** Connect timeout, milliseconds. */
    private int connectTimeout;
    /** Read timeout, milliseconds. */
    private long readTimeout;
}
