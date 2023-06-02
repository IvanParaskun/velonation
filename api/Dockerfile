FROM amazoncorretto:19.0.2-alpine as build
ADD . /build
WORKDIR /build
RUN ./mvnw package -DskipTests

FROM amazoncorretto:19.0.2-alpine
WORKDIR /app
COPY --from=build /build/target/api-1.0.jar /

EXPOSE 8080