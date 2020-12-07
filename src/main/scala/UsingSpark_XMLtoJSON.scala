import org.apache.spark.sql.SparkSession

object UsingSpark_XMLtoJSON extends App {

val session = SparkSession.builder().appName("MeteoStations").master("local").getOrCreate()

  val fPath = "./src/resources/HR_meta.xml"
  val df = session.read
    .format("xml")
    .option("rowTag", "station")
    .load(fPath)

  // creates new json file for each station

  val tmpPath = "./src/resources/meteoJSON"
  val fPathUnion = "./src/resources/newMeteoJSON"
  val stations = df.select("_Id", "station_info")
  stations
    .write.option("rootTag","station")
    .partitionBy("_Id")
    .format("json")
    .mode("overwrite")
    .save(tmpPath)

//  val dir = new File(tmpPath)
//  val tmpTsfFile = dir.listFiles.filter(_.toPath.toString.endsWith(".json"))(0).toString
//  (new File(tmpTsfFile)).renameTo(new File(fPathUnion))
//  dir.listFiles.foreach( f => f.delete ) //delete all the files in the directory
//  dir.delete //delete itself
}
