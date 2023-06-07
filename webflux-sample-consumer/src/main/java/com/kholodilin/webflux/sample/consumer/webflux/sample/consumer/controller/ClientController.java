package com.kholodilin.webflux.sample.consumer.webflux.sample.consumer.controller;

import com.kholodilin.webflux.sample.consumer.webflux.sample.consumer.dto.Client;
import com.kholodilin.webflux.sample.consumer.webflux.sample.consumer.dto.ClientResponse;
import com.kholodilin.webflux.sample.consumer.webflux.sample.consumer.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @PostMapping(path = "clients")
    public Flux<ClientResponse> moneyMove(@RequestBody Flux<Client> request) {
        return request
                .transform(clientFlux -> clientService.add(clientFlux));
    }
}
