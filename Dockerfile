## Build stage with full JDK
FROM eclipse-temurin:24-jdk-alpine AS builder
WORKDIR /app
COPY . .
RUN ./mvnw clean package -q -DskipTests

# Runtime stage with only JRE
FROM eclipse-temurin:24-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/flight-paths-1.0.0.jar flight-paths.jar
ENTRYPOINT ["java", "-jar", "flight-paths.jar"]