# mysparksql

a minimal code example of reading from cassandra via spark 2.x


client mode:

```console
/opt/spark/bin/spark-submit --master spark://<YOUR SPARK MASTER>:7077 --class onextent.demo.mysparksql.Main --driver-java-options="-DCASSANDRA_HOST=<YOUR CASSANDRA HOST(s)> -DKEYSPACE=<YOUR KEYSPACE> -DTABLE=<YOUR TABLE>" --deploy-mode client ./mysparksql.jar
```

via docker:

```console
docker run -e SPARK_MASTER=spark://<YOUR SPARK MASTER> -e CASSANDRA_HOST=<YOUR CASSANDRA HOST> -e KEYSPACE=<YOUR KEYSPACE> -e TABLE=<YOUR TABLE> -it navicore/mysparksql
```

via kubernetes job:

```yaml
apiVersion: batch/v1
kind: Job
metadata:
  name: mysparksql
spec:
  template:
    metadata:
      name: mysparksql
    spec:
      containers:
      - name: mysparksql
        image: navicore/mysparksql
        env: 
        - name: CASSANDRA_HOST
          value: "<YOUR CASSANDRA HOST>"
        - name: SPARK_MASTER
          value: "spark://<YOUR SPARK MASTER>"
        - name: KEYSPACE
          value: "<YOUR KEYSPACE>"
        - name: TABLE
          value: "<YOUR TABLE>"
      restartPolicy: Never
```

