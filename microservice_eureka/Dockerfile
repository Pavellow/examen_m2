FROM maven:3.8.3-openjdk-11-slim AS build-eureka
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM adoptopenjdk:11-jre-hotspot-focal
WORKDIR /app
COPY --from=build-eureka /app/target/*.jar app.jar
ENV JAVA_OPTS="-Xms256m -Xmx512m -XX:+UseContainerSupport"
ENV SPRING_PROFILES_ACTIVE=prod
ENV SERVER_PORT=8761
# Configuration spécifique pour Eureka
ENV EUREKA_CLIENT_REGISTER_WITH_EUREKA=false
ENV EUREKA_CLIENT_FETCH_REGISTRY=false
EXPOSE ${SERVER_PORT}
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
