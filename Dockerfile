FROM maven:3.9.6-eclipse-temurin-17 AS dependencies
WORKDIR /app
COPY pom.xml ./
RUN mvn dependency:go-offline

FROM dependencies AS build
COPY . .
RUN mvn  package -Dmaven.test.skip

FROM bellsoft/liberica-openjdk-alpine:21
WORKDIR /opt/app
COPY --from=build /app/target/*.jar app.jar
CMD ["sh", "-c", "java -jar $JAVA_OPTS app.jar"]
