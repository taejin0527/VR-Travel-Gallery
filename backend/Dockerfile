FROM openjdk:8-jdk-alpine
VOLUME /build
# ARG WAR_FILE=./build/libs/backend-0.0.1-SNAPSHOT.war
COPY ./build/libs/backend-0.0.1-SNAPSHOT.war app.war
ENTRYPOINT ["java", "-jar", "/app.war"]  