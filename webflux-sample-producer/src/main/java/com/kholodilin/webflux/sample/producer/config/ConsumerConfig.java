package com.kholodilin.webflux.sample.producer.config;

import com.kholodilin.webflux.sample.producer.config.property.ConsumerProperties;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
@RequiredArgsConstructor
@EnableConfigurationProperties({ConsumerProperties.class})
public class ConsumerConfig {

    private final ConsumerProperties properties;
    private final WebClient.Builder webClientBuilder;

    @Bean
    @SneakyThrows
    public WebClient webConsumerClient() {
        log.info("propertiesWebClient: {}", properties);

        var httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, properties.getConnection().getConnectTimeout())
                //.wiretap(log.isTraceEnabled())
                .doOnConnected(conn -> conn
                        .addHandlerLast(new ReadTimeoutHandler(properties.getConnection().getReadTimeout(), TimeUnit.MILLISECONDS)));


        return webClientBuilder
                .baseUrl(properties.getConnection().getUrl())
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
