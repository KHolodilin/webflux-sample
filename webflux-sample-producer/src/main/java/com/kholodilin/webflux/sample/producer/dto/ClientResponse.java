package com.kholodilin.webflux.sample.producer.dto;

import lombok.Data;

import java.util.UUID;

/**
 * Consumer response for a single client.
 * Field {@code client_id} stays snake_case so JSON matches the consumer side.
 */
@Data
public class ClientResponse {
    private UUID client_id;
    private String name;
}
