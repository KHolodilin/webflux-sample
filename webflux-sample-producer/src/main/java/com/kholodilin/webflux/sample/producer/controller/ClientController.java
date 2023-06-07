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

@RestController
@Slf4j
@RequiredArgsConstructor
public class ClientController {
    private final ConsumerRepository consumerRepository;

    @PostMapping(path = "generate")
    public Flux<ClientResponse> generate(@RequestBody Mono<Integer> size) {

        return size
                .flatMapMany(i ->
                {
                    log.info("Start generate {} clients", i);
                    return Flux.range(0, i.intValue());
                })
                .flatMap(integer -> {
                    var cln = Client
                            .builder()
                            .name(integer.toString())
                            .build();
                    log.info("Client build - {}", cln);
                    return Mono.just(cln);
                })
                .transform(clientFlux -> consumerRepository.send(clientFlux));
    }
}
