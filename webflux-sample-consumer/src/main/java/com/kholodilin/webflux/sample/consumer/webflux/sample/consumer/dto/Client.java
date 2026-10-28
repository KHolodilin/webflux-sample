package com.kholodilin.webflux.sample.consumer.webflux.sample.consumer.dto;

import lombok.Data;

/** Client received from the producer in the NDJSON stream. */
@Data
public class Client {
    private String name;
}
