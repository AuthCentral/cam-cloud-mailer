FROM eclipse-temurin
COPY target/cam-cloud-mailer-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","app.jar"]