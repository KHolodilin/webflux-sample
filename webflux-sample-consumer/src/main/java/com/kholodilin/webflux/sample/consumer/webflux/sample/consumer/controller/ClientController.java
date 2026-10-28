package com.kholodilin.webflux.sample.consumer.webflux.sample.consumer.controller;

import com.kholodilin.webflux.sample.consumer.webflux.sample.consumer.dto.Client;
import com.kholodilin.webflux.sample.consumer.webflux.sample.consumer.dto.ClientResponse;
import com.kholodilin.webflux.sample.consumer.webflux.sample.consumer.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * Accepts a client stream from the producer and returns a response stream.
 * Content-Type / Accept are NDJSON: items flow one by one, without buffering the full list.
 */
@RestController
@Slf4j
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    /**
     * For each inbound {@link Client}, returns a {@link ClientResponse}
     * (with a small artificial delay so the stream is easy to see).
     */
    @PostMapping(
            path = "clients",
            consumes = MediaType.APPLICATION_NDJSON_VALUE,
            produces = MediaType.APPLICATION_NDJSON_VALUE
    )
    public Flux<ClientResponse> receiveClients(@RequestBody Flux<Client> request) {
        return request.transform(clientService::add);
    }
}
