FROM maven:3.8.3-openjdk-11-slim AS build-dossier
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM adoptopenjdk:11-jre-hotspot-focal
WORKDIR /app
COPY --from=build-dossier /app/target/*.jar app.jar
ENV JAVA_OPTS="-Xms512m -Xmx1g -XX:+UseContainerSupport"
ENV SPRING_PROFILES_ACTIVE=prod
ENV SERVER_PORT=8083
# Configuration pour le stockage des documents
ENV MAX_FILE_SIZE=10MB
ENV MAX_REQUEST_SIZE=15MB
EXPOSE ${SERVER_PORT}
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
