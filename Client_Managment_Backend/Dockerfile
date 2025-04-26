# Etapa 1: Construir el JAR con Gradle
FROM gradle:8.7-jdk21-alpine AS builder

WORKDIR /app

COPY build.gradle .
COPY settings.gradle .
COPY src ./src

RUN gradle build --no-daemon -x test

# Etapa 2: runtime con Java
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

# ¡Sin nada extra después del ]!
ENTRYPOINT ["java", "-jar", "app.jar"]
