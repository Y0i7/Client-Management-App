# Client Management Backend

Backend de la aplicación **Client Management**, construido con Spring Boot, JPA/Hibernate y PostgreSQL.

---

## 📋 Descripción

Servicio REST que expone CRUD de clientes (entidad `Client`) con las siguientes operaciones:

| Método | Endpoint               | Descripción                                 |
|--------|------------------------|---------------------------------------------|
| GET    | `/api/clients`         | Listar todos los clientes                   |
| GET    | `/api/client/{id}`     | Obtener cliente por ID                      |
| GET    | `/api/name/{clientName}` | Buscar clientes por nombre (case-insensitive) |
| POST   | `/api/add`             | Crear nuevo cliente                         |
| PUT    | `/api/update/{id}`     | Actualizar cliente                          |
| DELETE | `/api/delete/{id}`     | Eliminar cliente                            |

---

## 🚀 Tecnologías

- Java 21 (toolchain)
- Spring Boot 3.3.5
- Spring Data JPA
- Hibernate
- PostgreSQL 15
- Lombok
- JUnit 5 + Mockito
- Embedded PostgreSQL para tests (`io.zonky.test:embedded-postgres`)
- Docker & Docker Compose

---

## 📁 Estructura del proyecto

```
Client_Managment_Backend/
├─ src/main/java/com/Client/Managment
│  ├─ Controller    ← REST controllers
│  ├─ Service       ← Lógica de negocio
│  ├─ Persistence
│  │  ├─ Entity     ← JPA entities
│  │  ├─ Repository ← Spring Data JPA
│  │  └─ Converter  ← DTO ↔ Entity
│  ├─ Domain        ← POJOs de dominio (DTO)
│  ├─ Exceptions    ← Custom exceptions
│  └─ config        ← WebConfig (CORS)
├─ src/main/resources
│  └─ application.properties
├─ src/test/java/...  ← Tests unitarios y de integración
├─ build.gradle
├─ Dockerfile        ← Multi-stage build Gradle → JRE
└─ .dockerignore
```

---

## ⚙️ Configuración

Variables definidas en `application.properties` (o en entorno via `SPRING_...`):

```properties
# DataSource
spring.datasource.url=jdbc:postgresql://postgres-db:5432/CLIENTS
spring.datasource.username=postgres
spring.datasource.password=admin1234
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# CORS (complementa WebConfig)
spring.cors.allowed-origins=http://localhost:3000

# Perfil activo (dev | prod)
spring.profiles.active=dev
```

> En Docker Compose estas propiedades se inyectan via variables de entorno `SPRING_DATASOURCE_URL`, etc.

---

## 🏃‍♂️ Ejecución Local

1. Compilar y ejecutar con Gradle:
   ```bash
   ./gradlew bootRun
   ```
2. La API quedará disponible en `http://localhost:8080/api`.
3. Verifica con `curl` o Postman:
   ```bash
   curl http://localhost:8080/api/clients
   ```

### Ejecutar tests

```bash
./gradlew test
```  
- Incluye pruebas unitarias (Mockito) y de repositorio con Embedded Postgres.

---

## 🐳 Docker

### .dockerignore

```text
.gradle/
build/
*.log
*.iml
.idea/
.vscode/
.env
```

### Dockerfile (multi-stage)

```dockerfile
# Stage 1: build JAR
FROM gradle:8.7-jdk21-alpine AS builder
WORKDIR /app
COPY build.gradle settings.gradle ./
COPY src ./src
RUN gradle build --no-daemon -x test

# Stage 2: runtime
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
```

### Levantar con Docker Compose

Definido en `docker-compose.yml` (junto a frontend):

```yaml
services:
  backend:
    build: ./Client_Managment_Backend
    ports: ["8080:8080"]
    environment:
      SPRING_DATASOURCE_URL: ...
      SPRING_DATASOURCE_USERNAME: ...
      SPRING_DATASOURCE_PASSWORD: ...
    depends_on:
      postgres-db:
        condition: service_healthy
    networks: [app-network]
```

Arranca todo:
```bash
docker compose up --build
```

---

## 🛠 Tests y Calidad

- **Service tests**: Mockito + JUnit 5 en `src/test/java/com/Client/Managment/Service`
- **Repository tests**: `@DataJpaTest` + Embedded Postgres en `src/test/java/com/Client/Managment/Persistence/Repository`
- **Controller tests**: `@WebMvcTest` + MockMvc en `src/test/java/com/Client/Managment/Controller`

Ejecuta:
```bash
./gradlew test
```

---

## 📄 Licencia

MIT © Yoi

