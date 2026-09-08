#!/usr/bin/env bash
set -e
SERVER_PORT="${SERVER_PORT:-27325}"
set -a
[ -f .env_5607c1ef-6921-41dc-b918-d9a7e2d5f477 ] && . ./.env_5607c1ef-6921-41dc-b918-d9a7e2d5f477
set +a
./mvnw package -DskipTests -q
exec java -jar target/app-0.1.0.jar --server.port=$SERVER_PORT
