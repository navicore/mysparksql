FROM navicore/spark:2.0.2a

EXPOSE 4040

RUN mkdir -p /app

ADD target/scala-2.11/*.jar /app/

WORKDIR /app

ENTRYPOINT ["/opt/spark/bin/spark-submit"]

