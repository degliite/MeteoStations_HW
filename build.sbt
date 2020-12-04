name := "MeteoStations_Spark"

version := "0.1"

scalaVersion := "2.12.12"

// https://mvnrepository.com/artifact/org.apache.spark/spark-core
libraryDependencies += "org.apache.spark" %% "spark-core" % "3.0.1"
// https://mvnrepository.com/artifact/org.apache.spark/spark-sql
libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.0.1"
// https://mvnrepository.com/artifact/com.databricks/spark-xml
libraryDependencies += "com.databricks" %% "spark-xml" % "0.10.0"
libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.3.0"
libraryDependencies += "org.json" % "json" % "20200518"