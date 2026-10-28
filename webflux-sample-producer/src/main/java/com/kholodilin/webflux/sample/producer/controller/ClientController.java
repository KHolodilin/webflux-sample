package com.kholodilin.webflux.sample.producer.controller;

import com.kholodilin.webflux.sample.producer.dto.Client;
import com.kholodilin.webflux.sample.producer.dto.ClientResponse;
import com.kholodilin.webflux.sample.producer.repository.ConsumerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Entry point of the producer service.
 * <p>
 * Takes a client count, builds a {@link Client} stream from it,
 * and streams that stream straight to the consumer via WebClient.
 */
@RestController
@Slf4j
@RequiredArgsConstructor
public class ClientController {
    private final ConsumerRepository consumerRepository;

    /**
     * Generates {@code size} clients and sends them to the consumer.
     * The response is a {@link ClientResponse} stream (each item arrives as the consumer finishes it).
     *
     * @param size how many clients to create (request body is a single number, e.g. {@code 10})
     */
    @PostMapping(path = "generate")
    public Flux<ClientResponse> generate(@RequestBody Mono<Integer> size) {
        return size
                .flatMapMany(count -> {
                    log.info("Start generate {} clients", count);
                    return Flux.range(0, count);
                })
                .map(index -> {
                    var client = Client.builder()
                            .name(index.toString())
                            .build();
                    log.info("Client build - {}", client);
                    return client;
                })
                .transform(consumerRepository::send);
    }
}
