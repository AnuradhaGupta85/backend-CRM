# Backend-CRM

Spring Boot 3.4 CRM API with JWT authentication, PostgreSQL persistence, Swagger and offset pagination.

## Run locally

```bash
chmod +x mvnw start.sh
bash ./start.sh
```

The app loads database credentials and JWT_SECRET from `.env_5607c1ef-6921-41dc-b918-d9a7e2d5f477`. It runs on port 27325 by default. Docker is intentionally not included.

## API

Swagger UI: `http://localhost:27325/docs`  
Health: `http://localhost:27325/actuator/health`

Public endpoints: `POST /api/v1/auth/register`, `POST /api/v1/auth/login`. All other endpoints require `Authorization: Bearer <token>`.

Resource CRUD endpoints (`POST`, `GET`, `GET /{id}`, `PUT /{id}`, `DELETE /{id}`): employees, attendances, tasks, leads, departments, leaves, announcements, notifications, meetings, customer-notes, reports. List endpoints accept `page` and `size`, default size 20.

## Build and test

```bash
./mvnw compile
./mvnw package -DskipTests
curl http://localhost:27325/actuator/health
```
