package com.kholodilin.webflux.sample.consumer.webflux.sample.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** Consumer: accepts an NDJSON client stream, processes it, and responds the same way. */
@SpringBootApplication
public class WebfluxSampleConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxSampleConsumerApplication.class, args);
    }

}
