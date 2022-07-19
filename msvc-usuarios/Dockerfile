FROM openjdk:17-jdk-alpine as builder
ARG MSVC_NAME=msvc_usuarios
WORKDIR /app/$MSVC_NAME

COPY ./pom.xml /app
COPY ./$MSVC_NAME/.mvn ./.mvn
COPY ./$MSVC_NAME/mvnw .
COPY ./$MSVC_NAME/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -rf ./target/

COPY ./$MSVC_NAME/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine
WORKDIR /app
RUN mkdir ./logs
ARG MSVC_NAME=msvc_usuarios
ARG TARGET_FOLDER=/app/$MSVC_NAME/target
COPY --from=builder $TARGET_FOLDER/${MSVC_NAME}-0.0.1-SNAPSHOT.jar .
ARG APP_PORT=8001
ENV PORT $APP_PORT
EXPOSE $PORT
# ENTRYPOINT ["java", "-jar", "msvc-usuarios-0.0.1-SNAPSHOT.jar"]

# nos permite sobreescribir el comando para poder hacer
# docker run -p 8001:8001 --rm -it usuarios /bin/bash
CMD ["java", "-jar", "msvc-usuarios-0.0.1-SNAPSHOT.jar"]
