package com.kholodilin.webflux.sample.consumer.webflux.sample.consumer.service;

import com.kholodilin.webflux.sample.consumer.webflux.sample.consumer.dto.Client;
import com.kholodilin.webflux.sample.consumer.webflux.sample.consumer.dto.ClientResponse;
import reactor.core.publisher.Flux;

/** Handles the inbound client stream. */
public interface ClientService {
    Flux<ClientResponse> add(Flux<Client> clients);
}
