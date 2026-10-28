package com.kholodilin.webflux.sample.consumer.webflux.sample.consumer.service;

import com.kholodilin.webflux.sample.consumer.webflux.sample.consumer.dto.Client;
import com.kholodilin.webflux.sample.consumer.webflux.sample.consumer.dto.ClientResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

/**
 * Fakes slow work: waits 3 seconds per client, then returns a response with a new {@code client_id}.
 * The delay is only for the streaming demo.
 */
@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

    private static final Duration PROCESSING_DELAY = Duration.ofSeconds(3);

    @Override
    public Flux<ClientResponse> add(Flux<Client> clients) {
        // flatMap on purpose: parallel processing, responses may arrive out of order.
        return clients.flatMap(client -> {
            log.info("Start - {}", client);
            return Mono.just(ClientResponse.builder()
                            .client_id(UUID.randomUUID())
                            .name(client.getName())
                            .build())
                    .delayElement(PROCESSING_DELAY);
        });
    }
}
