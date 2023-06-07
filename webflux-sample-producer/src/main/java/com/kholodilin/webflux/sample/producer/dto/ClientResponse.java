package com.kholodilin.webflux.sample.producer.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ClientResponse {
    private UUID client_id;
    private String name;
}
