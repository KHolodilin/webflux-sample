package com.kholodilin.webflux.sample.producer.dto;

import lombok.Builder;
import lombok.Data;

/** Client that the producer builds and streams to the consumer. */
@Data
@Builder
public class Client {
    private String name;
}
