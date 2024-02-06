FROM openjdk:17
EXPOSE 8181
WORKDIR /opt/app
COPY build/libs/*.jar library-app.jar
ENTRYPOINT ["java", "-jar", "library-app.jar"]