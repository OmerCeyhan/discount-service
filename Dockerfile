FROM maven:3.8.3-openjdk-17
WORKDIR /discount-service
COPY . .
RUN mvn clean install -DskipTests
ADD target/discount-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","app.jar"]