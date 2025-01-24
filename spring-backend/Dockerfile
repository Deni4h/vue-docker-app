FROM openjdk:17-jdk-slim

WORKDIR /app

COPY backend-java/target/backend-java-0.0.1-SNAPSHOT.jar app.jar
COPY backend-java/src/main/resources/daftar.json src/main/resources/daftar.json   

EXPOSE 8083

CMD ["java","-jar","app.jar"]