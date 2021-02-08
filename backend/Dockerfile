FROM openjdk:8-jdk-alpine
VOLUME /build
ARG WAR_FILE=backend-0.0.1-SNAPSHOT.war
COPY ${WAR_FILE} app.war
ENTRYPOINT ["java", "-jar", "/app.war"]  