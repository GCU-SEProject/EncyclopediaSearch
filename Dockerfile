FROM openjdk:17-jdk
VOLUME /tmp
WORKDIR /app
COPY target/EncyclopediaSearch-0.0.1-SNAPSHOT.jar encyclopediasearch.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "encyclopediasearch.jar"]