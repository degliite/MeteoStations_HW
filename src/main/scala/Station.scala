abstract class Station {
 val station_name: String
 val european_code: String
  val measurement_info: String
 override def toString: String = s"$measurement_info"
}

 abstract class MeteoData {
  val component_name: String
  val component_caption: String
  val measurement_unit: String
  val measurement_technique_principle: String
   val statistics: String

  override def toString: String = s"\n${component_name.toUpperCase}/$component_caption/ Unit: $measurement_unit,\n" +
    s"Technique: $measurement_technique_principle\n $statistics"
 }

abstract class Statistics {
  val year: Int
  val statistic_name: String

  override def toString =  s"$year: Statistic result:$statistic_name"
}



