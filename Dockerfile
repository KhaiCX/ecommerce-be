FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /ecommerce-be
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /ecommerce-be
COPY --from=build /ecommerce-be/target/*.jar ecommerce-be-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ecommerce-be-0.0.1-SNAPSHOT.jar"]