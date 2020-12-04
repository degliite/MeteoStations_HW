import scala.xml.XML.loadFile

object Report extends App {

  val stationsMetaData = loadFile("./src/resources/HR_meta.xml")

//  val country = (stationsMetaData \\ "airbase"\\ "country_name").text
  val country = (stationsMetaData \\ "country_name").text
  val stName = (stationsMetaData \\ "station_name").map(_.text)

println(s"In $country there are ${stName.size} Weather stations: \n${stName.mkString(";\n")}.")



}
