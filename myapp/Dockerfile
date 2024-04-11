FROM openjdk:21-jdk

ARG JAR_FILE=app/build/libs/app-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT [ "java", "-Dspring.profiles.active=prod", "-jar", "app.jar" ]