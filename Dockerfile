FROM gradle:7.6.3-jdk17-alpine AS build
WORKDIR /app

COPY src /app/src
COPY build.gradle gradle/wrapper /app/

RUN gradle build

FROM amazoncorretto:17.0.5
WORKDIR /app

COPY --from=build /app/build/libs/*.jar /app/app.jar
WORKDIR /app
ENV APP_NAME desafio-app
ENV SERVICE_NAMESPACE desafio-app
EXPOSE 8080 9090
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar app.jar"]

ENTRYPOINT ["java", "-jar", "app.jar"]