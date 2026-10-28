# Spring WebFlux producer / consumer (NDJSON)

![Java](https://img.shields.io/badge/Java-11-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.18-brightgreen)

Small Spring WebFlux demo: a producer builds a `Flux` of clients and streams them to a consumer over HTTP. Body format is NDJSON (`application/x-ndjson`) — one JSON object per line, so items can flow without waiting for the whole list.

The producer talks through `WebClient` with Reactor Netty connect/read timeouts. The consumer processes with `flatMap` on purpose, so responses can come back out of order.

Java 11, Spring Boot 2.7.

## Modules

- `webflux-sample-producer` — port `8880`, endpoint `POST /generate`
- `webflux-sample-consumer` — port `8881`, endpoint `POST /clients`

## Run

Start the consumer first:

```bash
cd webflux-sample-consumer
./mvnw spring-boot:run
```

Then the producer:

```bash
cd webflux-sample-producer
./mvnw spring-boot:run
```

Ask the producer to generate clients (body is just a number):

```bash
curl -N -X POST http://localhost:8880/generate \
  -H "Content-Type: application/json" \
  -d "5"
```

`-N` keeps the stream open so you can see NDJSON lines as they arrive. Each response has a `client_id` and a `name`. Consumer adds a short delay per item so the stream is easy to notice.

## Config

Producer talks to the consumer via `consumer-connection` in `webflux-sample-producer/src/main/resources/application.properties`:

- `consumer-connection.connection.url` — consumer base URL (`http://localhost:8881`)
- `consumer-connection.connection.connect-timeout` — TCP connect timeout, ms
- `consumer-connection.connection.read-timeout` — read timeout, ms
- `consumer-connection.retry.*` — retry settings are already in the properties; not wired into the WebClient chain yet

## What to look at

- Producer `ClientController` — builds the `Flux` and hands it to the repository
- `ConsumerRepositoryImpl` — `WebClient` POST with `APPLICATION_NDJSON` both ways
- `ConsumerConfig` — Reactor Netty timeouts on the client
- Consumer `ClientServiceImpl` — parallel `flatMap` + delay (order is intentionally not guaranteed)
