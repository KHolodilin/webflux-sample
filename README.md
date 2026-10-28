# Spring WebFlux producer / consumer (NDJSON)

![Java](https://img.shields.io/badge/Java-11-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.18-brightgreen)

Small Spring WebFlux demo: a producer builds a `Flux` of clients and streams them to a consumer over HTTP. Body format is NDJSON (`application/x-ndjson`) — one JSON object per line, so items can flow without waiting for the whole list.

The producer talks through `WebClient` with Reactor Netty connect/read timeouts. The consumer processes with `flatMap` on purpose, so responses can come back out of order.

Java 11, Spring Boot 2.7.

## Modules

| Module | Port | Endpoint | Role |
|---|---|---|---|
| `webflux-sample-producer` | `8880` | `POST /generate` | Builds a `Flux` of clients and streams them to the consumer via WebClient |
| `webflux-sample-consumer` | `8881` | `POST /clients` | Accepts NDJSON clients, processes with `flatMap`, returns NDJSON responses |

## Flow

Happy path for 3 clients:

```mermaid
sequenceDiagram
    autonumber
    actor User
    participant P as Producer :8880
    participant WC as WebClient
    participant C as Consumer :8881

    User->>P: POST /generate<br/>body: 3
    P->>P: Flux.range → Client(0..2)

    P->>WC: send(Flux&lt;Client&gt;)
    WC->>C: POST /clients<br/>Content-Type: application/x-ndjson

    Note over WC,C: NDJSON stream — one JSON object per line

    par parallel flatMap (order not guaranteed)
        C->>C: process Client "0" (+3s)
        C-->>WC: ClientResponse "0"
        C->>C: process Client "1" (+3s)
        C-->>WC: ClientResponse "1"
        C->>C: process Client "2" (+3s)
        C-->>WC: ClientResponse "2"
    end

    WC-->>P: Flux&lt;ClientResponse&gt;
    P-->>User: NDJSON responses<br/>(may arrive out of order)
```

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

| Property | Side | Meaning |
|---|---|---|
| `consumer-connection.connection.url` | producer → consumer | Consumer base URL (`http://localhost:8881`) |
| `consumer-connection.connection.connect-timeout` | producer | TCP connect timeout, ms |
| `consumer-connection.connection.read-timeout` | producer | Read timeout, ms |
| `consumer-connection.retry.max-attempts` | producer | Retry count (in properties; not wired into WebClient yet) |
| `consumer-connection.retry.min-backoff-ms` | producer | Min backoff before retry, ms |

## What to look at

- Producer `ClientController` — builds the `Flux` and hands it to the repository
- `ConsumerRepositoryImpl` — `WebClient` POST with `APPLICATION_NDJSON` both ways
- `ConsumerConfig` — Reactor Netty timeouts on the client
- Consumer `ClientServiceImpl` — parallel `flatMap` + delay (order is intentionally not guaranteed)
