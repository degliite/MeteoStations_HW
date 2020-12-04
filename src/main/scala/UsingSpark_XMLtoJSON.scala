import org.apache.spark.sql.SparkSession

object UsingSpark_XMLtoJSON extends App {

val session = SparkSession.builder().appName("MeteoStations").master("local").getOrCreate()

  val fPath = "./src/resources/GB_meta.xml"
  val df = session.read
    .format("xml")
    .option("rowTag", "station")
    .load(fPath)

  // creates new json file for each station
  val stations = df.select("_Id","measurement_configuration", "station_info")
  stations.write
    .option("rootTag","station")
    .partitionBy("_Id")
    .json("./src/resources/stations_meta.json")
}
