FROM amazoncorretto:17

WORKDIR /app

COPY target/service-0.0.1-SNAPSHOT.jar /app/service-0.0.1-SNAPSHOT.jar

EXPOSE 9820

ENTRYPOINT ["java", "-jar", "/app/service-0.0.1-SNAPSHOT.jar"]