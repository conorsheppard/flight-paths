FROM eclipse-temurin:24-jdk-alpine
WORKDIR /app
COPY target/flight-paths-1.0.0.jar flight-paths.jar
ENTRYPOINT ["java", "-jar", "flight-paths.jar"]
