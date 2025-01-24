FROM openjdk:17-jdk-slim

WORKDIR /app

COPY spring-backend/backend-java/target/backend-java-0.0.1-SNAPSHOT.jar app.jar
COPY spring-backend/backend-java/src/main/resources/daftar.json src/main/resources/daftar.json   

EXPOSE 8083

CMD ["java","-jar","app.jar"]