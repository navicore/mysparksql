FROM java:8

EXPOSE 4040

ADD target/scala-2.11/*.jar /app/
WORKDIR /app
ENTRYPOINT ["java", "-Xms500m", "-Xmx500m", "-jar", "/app/player.jar"]

