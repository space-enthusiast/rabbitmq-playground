# RabbitMQ Kotlin Playground

A minimal learning environment for AMQP consumer/producer patterns using RabbitMQ, Kotlin Script, and Docker.

## Prerequisites

- Docker & Docker Compose
- Kotlin (`kotlinc` on PATH) or IntelliJ IDEA

## Quick Start

### 1. Start RabbitMQ

```bash
docker compose up -d
```

Management UI: http://localhost:15672 (login `guest` / `guest`)

### 2. Run the Producer

```bash
kotlin Producer.main.kts
```

### 3. Run the Consumer

```bash
kotlin Consumer.main.kts
```

In IntelliJ, you can also open any `.main.kts` file and click the green run gutter icon.

## Tear Down

Stop containers:

```bash
docker compose down
```

Stop containers **and delete stored data**:

```bash
docker compose down -v
```
