package com.kholodilin.webflux.sample.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** Producer: builds a client stream and sends it to the consumer via WebClient (NDJSON). */
@SpringBootApplication
public class WebfluxSampleProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxSampleProducerApplication.class, args);
    }

}
