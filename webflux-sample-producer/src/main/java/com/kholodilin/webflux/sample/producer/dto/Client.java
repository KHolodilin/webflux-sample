package com.kholodilin.webflux.sample.producer.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Client {
    private String name;
}
