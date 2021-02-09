FROM openjdk:8-jdk-alpine
VOLUME /build
ADD ./build/libs/backend-0.0.1-SNAPSHOT.war app.war
ENV JAVA_OPTS=""
ENTRYPOINT ["java", "-jar", "app.war", "--server.servlet.context-path=/apis"]  