FROM openjdk:17
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} repair-ms.jar
ENTRYPOINT ["java","-jar","/repair-ms.jar"]