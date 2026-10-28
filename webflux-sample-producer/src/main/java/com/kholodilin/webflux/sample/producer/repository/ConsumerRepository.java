package com.kholodilin.webflux.sample.producer.repository;

import com.kholodilin.webflux.sample.producer.dto.Client;
import com.kholodilin.webflux.sample.producer.dto.ClientResponse;
import reactor.core.publisher.Flux;

/** Client to the consumer service: outbound client stream, inbound response stream. */
public interface ConsumerRepository {
    Flux<ClientResponse> send(Flux<Client> clients);
}
