package com.kholodilin.webflux.sample.consumer.webflux.sample.consumer.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

/**
 * Response for a single client.
 * {@code client_id} is snake_case to match the JSON field on the producer side.
 */
@Data
@Builder
public class ClientResponse {
    private UUID client_id;
    private String name;
}
