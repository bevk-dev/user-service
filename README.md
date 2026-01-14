# user-service

Kratek opis
- Upravljanje uporabnikov, profili in avtentikacija. Shrani uporabniške podatke v Postgres.

Gradnja

```bash
# v mapi user-service
./mvnw clean package -DskipTests
docker build -t <your-registry>/user-service:latest .
```

Zagon
- Lokalno z Docker Compose: `shopsync-infra/docker-compose.yml` (izpostavljen kot `8083:8083`).
- Kubernetes manifests: `shopsync-infra/k8s/user-service`.

Pogosti env spremenljivke
- `SPRING_DATASOURCE_URL` — JDBC povezava na `user_db`.
- `SPRING_KAFKA_BOOTSTRAP_SERVERS` — Kafka bootstrap server.

Konfiguracija
- `src/main/resources/application.yml` ali `application.properties`
