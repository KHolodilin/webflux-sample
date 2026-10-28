package com.kholodilin.webflux.sample.producer.repository;

import com.kholodilin.webflux.sample.producer.config.property.ConsumerProperties;
import com.kholodilin.webflux.sample.producer.dto.Client;
import com.kholodilin.webflux.sample.producer.dto.ClientResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

/**
 * Sends clients to the consumer as an NDJSON stream and reads the response the same way.
 * <p>
 * {@code APPLICATION_NDJSON} is newline-delimited JSON: one object per line.
 * Producer and consumer can exchange items one by one without waiting for the full list.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerRepositoryImpl implements ConsumerRepository {
    private final WebClient webConsumerClient;
    private final ConsumerProperties consumerProperties;

    @Override
    public Flux<ClientResponse> send(Flux<Client> clients) {
        log.debug("Sending clients to {}", consumerProperties.getConnection().getUrl());

        return webConsumerClient
                .post()
                .uri("clients")
                .contentType(MediaType.APPLICATION_NDJSON)
                .accept(MediaType.APPLICATION_NDJSON)
                .body(clients, Client.class)
                .retrieve()
                .bodyToFlux(ClientResponse.class);
    }
}
