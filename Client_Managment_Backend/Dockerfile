# Etapa 1: Construir el JAR con Gradle
FROM gradle:8.7-jdk21-alpine AS builder

WORKDIR /app

# Copiar solo los archivos necesarios para la construcción
COPY build.gradle .
COPY settings.gradle .
COPY src ./src

# Construir el proyecto y generar el JAR
RUN gradle build --no-daemon -x test

# Etapa 2: Crear la imagen final con el JAR
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copiar el JAR desde la etapa de construcción
COPY --from=builder /app/build/libs/*.jar app.jar

# Exponer el puerto de la aplicación
EXPOSE 8080

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]"-b"]