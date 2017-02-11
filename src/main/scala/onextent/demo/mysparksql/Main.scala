package onextent.demo.mysparksql

import com.typesafe.config.{Config, ConfigFactory}
import com.typesafe.scalalogging.LazyLogging
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

object Main extends LazyLogging with Query {

  def main(args: Array[String]): Unit = {

    val config: Config = ConfigFactory.load().getConfig("main")

    val sparkConf = new SparkConf()
      .setAppName("bits.MySparkSQL")
      .set("spark.sql.shuffle.partitions", "8")
      .setIfMissing("spark.master", "local[*]") //for running from intellij
      .set("spark.cassandra.connection.host", config.getString("cassandraHost"))
      .set("spark.cassandra.auth.username", "cassandra")
      .set("spark.cassandra.auth.password", "cassandra")

    val sc = new SparkContext(sparkConf)

    val sqlContext = new SQLContext(sc)

    query(sqlContext)

    sc.stop()
  }

}

