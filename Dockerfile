FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/*.jar ecommerce-be-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "ecommerce-be-0.0.1-SNAPSHOT.jar"]
