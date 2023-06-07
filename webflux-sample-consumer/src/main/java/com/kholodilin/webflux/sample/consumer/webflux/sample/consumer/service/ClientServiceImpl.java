package com.kholodilin.webflux.sample.consumer.webflux.sample.consumer.service;

import com.kholodilin.webflux.sample.consumer.webflux.sample.consumer.dto.Client;
import com.kholodilin.webflux.sample.consumer.webflux.sample.consumer.dto.ClientResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {
    @Override
    public Flux<ClientResponse> add(Flux<Client> clients) {
        return clients.flatMap(client -> {
            log.info("Start - {}", client);
            return Mono.just(ClientResponse
                            .builder()
                            .client_id(UUID.randomUUID())
                            .name(client.getName())
                            .build())
                    .delayElement(Duration.ofSeconds(3));
        });
    }
}
