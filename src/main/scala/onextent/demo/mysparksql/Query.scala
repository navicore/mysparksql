package onextent.demo.mysparksql

import com.typesafe.config.{Config, ConfigFactory}
import com.typesafe.scalalogging.LazyLogging
import org.apache.spark.sql.SQLContext

trait Query extends LazyLogging {

  val config: Config = ConfigFactory.load().getConfig("main")

  def query(sqlContext: SQLContext): Unit = {

    sqlContext.setConf("default:test/spark.cassandra.input.split.size_in_mb",
                       "128")

    val howRows = 5

    logger.info(s"running query")

    val df = sqlContext.read
      .format("org.apache.spark.sql.cassandra")
      .options(Map("table" -> s"${config.getString("table")}",
                   "keyspace" -> s"${config.getString("keyspace")}"))
      .load()

    df.show(howRows)

  }

}
