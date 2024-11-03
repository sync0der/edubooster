FROM openjdk:21

#WORKDIR /app

COPY target/ricoin.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "ricoin.jar"]