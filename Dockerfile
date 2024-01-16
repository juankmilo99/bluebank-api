#
# Build stage
#
FROM gradle:jdk18 AS build
WORKDIR /app
COPY . .
RUN gradle clean build

#
# Package stage
#
FROM adoptopenjdk:16-jre-hotspot-slim
WORKDIR /app
COPY --from=build /app/build/libs/accounts-0.0.1-SNAPSHOT app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
