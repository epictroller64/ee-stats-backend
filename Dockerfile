# Build stage
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY target/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]