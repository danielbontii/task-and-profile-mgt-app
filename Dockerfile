FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app/
COPY . /app/
RUN mvn clean package -DskipTests
#
# Package stage
#
FROM eclipse-temurin:17-jdk
COPY --from=build /app/target/tpms-backend.jar app.jar
EXPOSE 10000
ENTRYPOINT ["java","-jar","app.jar"]