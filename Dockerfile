FROM openjdk:11

COPY build/libs/recipe-service-0.0.1-SNAPSHOT.jar app.jar

ENV JAVA_OPTS=""

ENTRYPOINT exec java -jar /app.jar