FROM navicore/spark:2.0.2a

EXPOSE 4040

RUN mkdir -p /app

ADD target/scala-2.11/*.jar /app/

WORKDIR /app

CMD /opt/spark/bin/spark-submit --master $SPARK_MASTER --class onextent.demo.mysparksql.Main --driver-java-options="-DCASSANDRA_HOST=$DCASSANDRA_HOST -DKEYSPACE=$KEYSPACE -DTABLE=$TABLE" --deploy-mode client ./mysparksql.jar

