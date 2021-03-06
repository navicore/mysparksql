name := "MySparkSQL"

version := "1.0"

scalaVersion := "2.11.8"

val sparkVersion = "2.0.2"

libraryDependencies ++=
    Seq(
      "ch.qos.logback" % "logback-classic" % "1.1.7",
      "com.datastax.spark" % "spark-cassandra-connector_2.11" % "2.0.0-M3",
      "com.rollbar" % "rollbar" % "0.5.3",
      "com.typesafe" % "config" % "1.2.1",
      "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0",
      "joda-time" % "joda-time" % "2.6",
      "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
      "org.apache.spark" %% "spark-sql" % sparkVersion % "provided",
      "org.json4s" %% "json4s-ext" % "3.5.0",
      "org.json4s" %% "json4s-jackson" % "3.5.0",
      "org.scalaj" %% "scalaj-http" % "2.3.0"
    )

mainClass in assembly := Some("onextent.demo.mysparksql.Main")
assemblyJarName in assembly := "mysparksql.jar"

assemblyMergeStrategy in assembly := {
  case x if x.endsWith("io.netty.versions.properties") => MergeStrategy.first
  case PathList("org",   "slf4j", xs @ _*) => MergeStrategy.last
  case PathList("org",   "apache", xs @ _*) => MergeStrategy.last
  case PathList("org",   "aopalliance", xs @ _*) => MergeStrategy.last
  case PathList("javax", "ws", xs @ _*) => MergeStrategy.last
  case PathList("javax", "servlet", xs @ _*) => MergeStrategy.last
  case PathList("javax", "inject", xs @ _*) => MergeStrategy.last
  case PathList("com",   "sun", xs @ _*) => MergeStrategy.last
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

// scala lint
lazy val compileScalastyle = taskKey[Unit]("compileScalastyle")
compileScalastyle := org.scalastyle.sbt.ScalastylePlugin.scalastyle.in(Compile).toTask("").value

(compile in Compile) <<= (compile in Compile) dependsOn compileScalastyle

lazy val testScalastyle = taskKey[Unit]("testScalastyle")
testScalastyle := org.scalastyle.sbt.ScalastylePlugin.scalastyle.in(Test).toTask("").value

