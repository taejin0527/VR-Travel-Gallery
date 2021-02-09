FROM openjdk:8-jdk-alpine
VOLUME /build
ADD ./build/libs/backend-0.0.1-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["java", "-jar", "app.jar", "--server.servlet.context-path=/apis"]  