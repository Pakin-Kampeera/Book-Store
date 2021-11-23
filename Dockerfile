# Build
FROM maven:3.8.4-jdk-11 as maven
COPY ./pom.xml ./pom.xml
RUN mvn dependency:go-offline -B
COPY ./src ./src
RUN mvn package -DskipTests

# Deploy
FROM openjdk:11
WORKDIR /book-store
COPY --from=maven target/*.jar app.jar
CMD ["java", "-jar", "./app.jar"]