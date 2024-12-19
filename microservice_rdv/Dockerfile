FROM maven:3.8.3-openjdk-11-slim AS build-rdv
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM adoptopenjdk:11-jre-hotspot-focal
WORKDIR /app
COPY --from=build-rdv /app/target/*.jar app.jar
ENV JAVA_OPTS="-Xms512m -Xmx1g -XX:+UseContainerSupport"
ENV SPRING_PROFILES_ACTIVE=prod
ENV SERVER_PORT=8082
# Configuration supplémentaire pour Hystrix et Retry
ENV HYSTRIX_TIMEOUT=20000
ENV RETRY_MAX_ATTEMPTS=3
EXPOSE ${SERVER_PORT}
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
