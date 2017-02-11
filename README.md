# mysparksql

a minimal code example of reading from cassandra via spark 2.x


client mode:

```console
/opt/spark/bin/spark-submit --master spark://<YOUR SPARK MASTER>:7077 --class onextent.demo.mysparksql.Main --driver-java-options="-DCASSANDRA_HOST=<YOUR CASSANDRA HOST(s)> -DKEYSPACE=<YOUR KEYSPACE> -DTABLE=<YOUR TABLE>" --deploy-mode client ./mysparksql.jar
```

