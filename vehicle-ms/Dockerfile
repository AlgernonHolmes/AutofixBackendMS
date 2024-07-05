FROM openjdk:17
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} vehicle-ms.jar
ENTRYPOINT ["java","-jar","/vehicle-ms.jar"]