#Build stage katjme3 lmechrou3 o katbni app.jar bisti3mal maven
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests
#Runtime stage  katkhdem tetbi9 bisti3mal java bla maven
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8091
ENTRYPOINT ["java", "-jar", "app.jar"]
