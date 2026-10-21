# iso-service-process [working in progress]

A Spring Boot backend for a appointment booking workflow, built as a hands-on project for practising modern Java and containerised deployment.

## What it does

- Create a booking (`POST /api/bookings`) with client details, appointment time and a service provider chosen by name
- Look up a booking by id (`GET /api/bookings/{id}`)
- Search bookings by client name, case-insensitive partial match (`GET /api/bookings/search/name?name=...`)
- Booking status is modelled as a sealed interface (`Submitted`, `PendingReview`, `Approved`, `Rejected`) and mapped to a flat JPA entity
- Global exception handling returns structured JSON errors with proper HTTP status codes

## Tech stack

- Java 21, Spring Boot 4.1.x, Spring Data JPA (Hibernate)
- PostgreSQL 17
- Maven, Docker, Docker Compose
- Spring Boot Actuator (health checks)

## Run with Docker

Prerequisite: Docker Desktop is running.

```bash
copy .env.example .env      # then edit .env and set a real password
docker build -t iso-service-process:1.0 .
docker-compose up -d
```

Make sure the `image:` tag in `docker-compose.yml` matches the tag you built.

Check it is healthy:

```bash
docker-compose ps
curl http://localhost:8080/api/actuator/health
```

Stop everything (database data is kept in a Docker volume):

```bash
docker-compose down
```

`docker-compose down -v` also deletes the database volume.

## Run locally (without Docker for the app)

1. Start only the database: `docker-compose up -d postgres`
2. Set the environment variable `POSTGRES_PASSWORD` (same value as in `.env`)
3. Run `ProcessApplication` from your IDE, or `./mvnw spring-boot:run`

Note: `./mvnw package` runs `ProcessApplicationTests`, which needs the database running.

## Configuration

| Variable | Purpose |
|---|---|
| `POSTGRES_PASSWORD` | Database password, read from `.env` by Docker Compose |
| `SPRING_DATASOURCE_URL` | Set in `docker-compose.yml` so the app reaches the `postgres` container |
| `JAVA_TOOL_OPTIONS` | JVM flags, e.g. `-XX:MaxRAMPercentage=70` |

`.env` is git-ignored. Never commit real credentials.

## Container notes

- Multi-stage build: JDK to compile, JRE to run; Spring Boot layered jar so dependency layers stay cached
- Runs as a non-root user
- Memory limit 512 MB with `MaxRAMPercentage=70`, leaving room for non-heap memory
- Postgres healthcheck; the app starts only after the database is healthy

## Known limitations

- `spring.jpa.hibernate.ddl-auto: update` is for development only; production would use a migration tool such as Flyway
- No authentication, input validation or concurrency control yet
- Tests are minimal
- <add anything else you want to be honest about>

## Roadmap

- Studio service and inter-service calls
- EOD reconciliation job (expire bookings pending more than 7 days)
- Input validation, unit tests
- Kubernetes manifests
