FROM eclipse-temurin:17-jdk

ARG FILE_JAR=target/*jar

ADD ${FILE_JAR} api-service-document.jar

ENTRYPOINT ["java", "-jar", "api-service-document.jar"]

EXPOSE 8080