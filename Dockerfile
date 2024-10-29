FROM maven:3.9.9-eclipse-temurin-17-alpine AS build
COPY . .
RUN mvn package -DskipTests

FROM maven:3.9.9-eclipse-temurin-17-alpine
COPY --from=build target/CompraOQ.jar app.jar
EXPOSE 8080
CMD ["java","-jar","app.jar"]