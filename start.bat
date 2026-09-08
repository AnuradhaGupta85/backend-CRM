@echo off
if not defined SERVER_PORT set SERVER_PORT=27325
for /f "usebackq tokens=1,* delims==" %%A in (.env_5607c1ef-6921-41dc-b918-d9a7e2d5f477) do set %%A=%%B
call mvnw.cmd package -DskipTests -q
java -jar target\app-0.1.0.jar --server.port=%SERVER_PORT%
