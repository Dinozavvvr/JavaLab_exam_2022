#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/PdfGenerator-0.1.jar /usr/local/lib/app.jar
COPY templates /usr/local/lib/
COPY templates /templates
EXPOSE 8080
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","/usr/local/lib/app.jar"]
