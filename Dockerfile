FROM openjdk:17-jdk-slim

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} weshlists.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/weshlists.jar"]
