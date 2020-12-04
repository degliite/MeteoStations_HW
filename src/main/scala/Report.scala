import scala.xml.XML.{loadFile, xml}

object Report extends App {

  val stationsMetaData = loadFile("./src/resources/GB_meta.xml")
  val stName = stationsMetaData \\ "airbase"\\ "station_name"
  val stList =  stName.map(_.text)
println(stList)

}
