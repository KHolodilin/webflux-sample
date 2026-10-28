# Contributing

Thanks for your interest in contributing! This document explains how to get set up
and what to expect from the process.

## Requirements

- Java 11+
- Maven 3.9+

## Building

Producer and consumer are separate Spring Boot modules. From each module:

```bash
cd webflux-sample-consumer
./mvnw clean verify

cd ../webflux-sample-producer
./mvnw clean verify
```

## Run locally

Start the consumer first (port `8881`), then the producer (port `8880`). See [README.md](README.md)
for curl examples and config.

## Pull requests

1. Fork the repository and create a branch from `main`.
2. Make your change; add or update tests for any behavior change.
3. Run `./mvnw clean verify` in the module(s) you touched.
4. Open a pull request with a clear description of the motivation and the change.

Keep pull requests focused: one logical change per PR is much easier to review.

## Reporting bugs and requesting features

Use the [issue templates](https://github.com/KHolodilin/webflux-sample/issues/new/choose).
For bugs, include Spring Boot / Java versions and a minimal reproduction if possible.

## Code style

- Comments explain *why*, not *what*.
- Keep the sample easy to follow: prefer clear naming over cleverness.
- NDJSON streaming and intentional `flatMap` ordering are part of the demo — preserve that behavior unless the PR is explicitly changing it.
