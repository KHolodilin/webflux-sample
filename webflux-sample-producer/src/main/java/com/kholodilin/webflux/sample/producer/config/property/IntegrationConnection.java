package com.kholodilin.webflux.sample.producer.config.property;

import lombok.Data;

@Data
public class IntegrationConnection {
    private String url;
    private String endPointName;
    private int connectTimeout;
    private long readTimeout;
}
