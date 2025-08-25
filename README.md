# 🚀 Hello, World! - Kafka producer app

Minimal Spring Boot Kafka producer that periodically sends a greeting message to a Kafka topic. Useful as a local quickstart and a template for producing messages with Spring Kafka.

## 🧭 What it does

- Schedules a task every 10s to produce `Greeting("Hello, World!")` via `KafkaTemplate`
- Uses topic definition key `hello-world` with a configurable topic name (default: `hello.world`)
- Exposes Actuator health/metrics and Prometheus scrape endpoint

## 🛠️ Prerequisites

- Git CLI — see `https://help.github.com/articles/set-up-git`
- Docker and Docker Compose — see `https://docs.docker.com/get-docker/` and `https://docs.docker.com/compose/install/`
- Java 21 (project targets Java 21)

## ⚡ Quickstart

1. Clone and open the project

```bash
git clone https://github.com/IQKV/quickstart-kafka-producing-hello.git
cd quickstart-kafka-producing-hello
```

2. Start Kafka and observability stack (ZooKeeper, Kafka broker, Prometheus, Grafana)

```bash
docker compose -f compose.yaml up -d
```

3. Run the app (local profile + dev tools)

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=local -P dev
```

Alternatively, build and run the jar:

```bash
./mvnw package
java -jar target/*.jar
```

## ⚙️ Configuration

Key properties and environment variables (defaults shown):

- **Kafka connection**

  - `KAFKA_BOOTSTRAP_SERVERS` (default `localhost:9092`)
  - `KAFKA_SECURITY_PROTOCOL` (default `PLAINTEXT`)

- **Topic definition (application-configuration.kafka.topics.hello-world)**

  - `KAFKA_TOPIC_HELLO_WORLD` (topic name, default `hello.world`)
  - `KAFKA_TOPIC_PARTITIONS_HELLO_WORLD` (default `2`)
  - `KAFKA_TOPIC_RETENTION_HELLO_WORLD` (default `30m`)
  - `KAFKA_ADMIN_CREATES_TOPICS` (default `true`) — auto-create on startup

- **Ports**
  - `SERVER_PORT` (default `8080`)
  - `MANAGEMENT_SERVER_PORT` (default `8080`) — Actuator

MacOS note (Prometheus/Grafana): follow the comments in `compose.yaml` and replace `localhost` with `host.docker.internal` in Prometheus/Grafana configs if needed.

## 🔁 How it works

- `ServiceNotifier#scheduledCall()` runs every 10 seconds and calls `GreetingMessageProducer#send()`
- The producer uses `KafkaTemplate<String, Greeting>` and topic definitions loaded from configuration
- Topic key: `hello-world`; topic name comes from `application.yml` (default `hello.world`)

## 📡 Endpoints

- App base: `http://localhost:8080`
- Actuator (selectively exposed):
  - Health: `http://localhost:8080/actuator/health`
  - Info: `http://localhost:8080/actuator/info`
  - Env: `http://localhost:8080/actuator/env`
  - Metrics: `http://localhost:8080/actuator/metrics`
  - Prometheus: `http://localhost:8080/actuator/prometheus`
  - Kafka: `http://localhost:8080/actuator/kafka`

## 📊 Observability

- Prometheus: runs via Docker, scraping the app’s `/actuator/prometheus`
- Grafana: default admin password is `changeme` (see `compose.yaml`)
  - UI: `http://localhost:3000` (host network mode)
  - Pre-provisioned dashboard and datasource under `src/main/docker/grafana/provisioning`

## ✅ Tests

Run the full test suite (uses Testcontainers profile):

```bash
./mvnw verify -P use-testcontainers
```

The build enforces high coverage thresholds (JaCoCo) and integrates with static analysis (Checkstyle, SpotBugs, PMD, SonarQube).

## 🧹 Code conventions

Follows the [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html). Quality gates/tools used:

- [SonarQube](https://docs.sonarsource.com/)
- [PMD](https://pmd.github.io/)
- [CheckStyle](https://checkstyle.sourceforge.io/)
- [SpotBugs](https://spotbugs.github.io/)
- [Qulice](https://www.qulice.com/)

## 🛟 Troubleshooting

- **Kafka connection errors**: ensure Docker is running and `localhost:9092` is reachable; verify `KAFKA_BOOTSTRAP_SERVERS`
- **Port already in use**: change `SERVER_PORT`/`MANAGEMENT_SERVER_PORT` or stop the conflicting service
- **Prometheus/Grafana not showing data**: confirm app is running and `/actuator/prometheus` is accessible
- **Mac networking**: use `host.docker.internal` as noted in `compose.yaml`
