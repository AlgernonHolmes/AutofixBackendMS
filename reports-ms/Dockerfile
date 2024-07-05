FROM openjdk:17
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} reports-ms.jar
ENTRYPOINT ["java","-jar","/reports-ms.jar"]