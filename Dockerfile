FROM mcr.microsoft.com/openjdk/jdk:21-ubuntu

WORKDIR /app

COPY build/libs/procustomer-0.0.1-SNAPSHOT.jar procustomer.jar

# Download the OpenTelemetry Java agent and copy it to the container
ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v2.2.0/opentelemetry-javaagent.jar /app/opentelemetry-javaagent.jar

EXPOSE 8080

ENTRYPOINT ["java", "-javaagent:/app/opentelemetry-javaagent.jar", "-jar","procustomer.jar"]