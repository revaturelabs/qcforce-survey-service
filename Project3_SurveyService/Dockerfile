FROM openjdk:8-jdk-alpine
ADD target/*.jar survey-service.jar
EXPOSE 8087
ENTRYPOINT ["java", "-jar", "survey-service.jar"]