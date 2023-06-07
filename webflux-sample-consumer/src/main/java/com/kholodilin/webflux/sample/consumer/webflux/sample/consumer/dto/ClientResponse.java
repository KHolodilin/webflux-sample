package com.kholodilin.webflux.sample.consumer.webflux.sample.consumer.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ClientResponse {
    private UUID client_id;
    private String name;
}
