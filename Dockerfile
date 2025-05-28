# Build stage
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests
# Debug: List contents of target directory
RUN ls -la target/
RUN echo "Contents of target directory:"
RUN find target -type f

# Run stage
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
# Copy the specific JAR file
COPY --from=build /app/target/initial-0.0.1-SNAPSHOT.jar app.jar
# Debug: Verify the jar exists
RUN ls -la /app/
RUN echo "Contents of /app directory:"
RUN find /app -type f

# Set environment variables
ENV JAVA_OPTS="-Xms512m -Xmx512m -XX:+UseG1GC"

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]