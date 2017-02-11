package onextent.demo.mysparksql

import java.util.Properties

import com.github.benfradet.spark.kafka010.writer._
import com.typesafe.scalalogging.LazyLogging
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{LongType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Row, SQLContext}

trait Query extends LazyLogging {

  def query(sqlContext: SQLContext): Unit = {

    sqlContext.setConf("default:test/spark.cassandra.input.split.size_in_mb", "128")

    val SHOW_ROWS = 5
    //df.show(SHOW_ROWS)

    logger.info(s"running query")

    //select * from storemastersubset2;
    val df = sqlContext
      .read
      .format("org.apache.spark.sql.cassandra")
      .options(Map( "table" -> "storemastersubset2", "keyspace" -> "pos"))
      .load()

    df.show(SHOW_ROWS)

    /*
    val df = conf.csv.toOption match {
      case Some(true) => readDelimited(sqlContext, conf)
      case _ => sqlContext.read.json(conf.file())
    }


    val rdd = df.rdd.zipWithIndex()
      .map { case (row, i) => Row.fromSeq(row.toSeq :+ i) }
    val schema = StructType(df.schema.fields :+ StructField("index", LongType))
    val df2 = sqlContext.createDataFrame(rdd, schema)
    df2.cache()

    val count = df2.count()
    logger.info(s"Loaded data: count=$count, columns=${df.toString()}")

    val producerConfig = {
      val p = new Properties()
      p.setProperty("bootstrap.servers", conf.brokerList())
      p.setProperty("key.serializer", classOf[StringSerializer].getName)
      p.setProperty("value.serializer", classOf[StringSerializer].getName)
      p
    }

    var topic = conf.topic()
    var batch = conf.batch()
    var delay = conf.delay()
    var from = 0L
    while (from < count) {
      val to = math.min(from + batch, count)
      logger.info(s"Publishing data: from=$from, to=$to")
      val sample: RDD[String] = df2.filter(col("index") >= from && col("index") < to).drop("index").toJSON.rdd
      sample.writeToKafka(
        producerConfig,
        s => new ProducerRecord[String, String](topic, s)
      )
      Thread.sleep(delay)
      from = to
    }

    df2.unpersist()
     */

  }

}
