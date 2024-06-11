FROM maven:3.8.4-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21
COPY --from=build /target/asn_2-0.0.1-SNAPSHOT.jar /opt/app/asn_2.jar
CMD ["java", "-jar", "/opt/app/asn_2.jar"]
