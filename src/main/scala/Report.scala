import java.io.{File, FileWriter}

import scala.xml.{Node, NodeSeq, XML}
import scala.xml.XML.loadFile
import org.json.XML

object Report extends App {

  //To read the XML file
  val stationsMetaData = loadFile("./src/resources/HR_meta.xml")

  val country = (stationsMetaData \\ "country_name").text
  val stations = stationsMetaData \\ "station"
  val stName = (stationsMetaData \\ "station_name").map(_.text)

  println(s"In $country there are ${stName.size} Weather stations: \n${stName.mkString(";\n")}.")
  println("\n")

  //To get a specific info for the Station
  def getStationMeta(el: Node): Station =
    new Station {
      val station_name: String = (el \\ "station_name").text
      val european_code: String = (el \\ "station_european_code").text
      val measurement_info: String = (el \\ "measurement_info").map(getMeteoData).mkString("\n")
      val statistics: String = (el \\ "statistics").map(getStatistics).mkString("\n")

      override def toString: String = s"STATION: $station_name(code:$european_code)\n \nMEASUREMENT INFO:$measurement_info $statistics"
    }

  // to get measurement meta data
  def getMeteoData(el: Node): MeteoData =
    new MeteoData {
      val component_name: String = (el \ "component_name").text
      val component_caption: String = (el \ "component_caption").text
      val measurement_unit: String = (el \ "measurement_unit").text
      val measurement_technique_principle: String = (el \ "measurement_technique_principle").text
      val statistics: String = (el \ "statistics").map(getStatistics).mkString("\n")
    }

  // to get statistics
  def getStatistics(el: Node): Statistics =
    new Statistics {
      val year: Int = (el \ "@Year").text.toInt
      val statistic_name: String = (el \\ "statistic_result").filter(i => i.toString.contains("P50")).head.text
    }

// To get all stations in a Seq
  def getStationSeq(stationNodes: NodeSeq): Seq[Station] = {
    for (station <- stationNodes) yield getStationMeta(station)
  }


// To save file in TSV
  def saveTSV(el: Station): Unit = {
    val station_name = el.station_name
    val european_code = el.european_code
    val writer = new FileWriter(new File(s"./src/resources/stations_TSV/${station_name}_${european_code}_yearly.tsv"))
    writer.write("Report by station\n\n" + el.toString)
    writer.close()
  }

  // Performing the saving
  val data = getStationSeq(stations)
  data.foreach(saveTSV)
}
