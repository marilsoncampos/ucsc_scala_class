package utils

import org.apache.spark.sql.{DataFrame, SparkSession}


class CsvReader(val spark: SparkSession) {
  def loadFile(fileLocation: String, sep: String = ","): DataFrame = {
    spark.read.format("csv")
      .option("inferSchema", value = true)
      .option("header", value = true)
      .option("sep", sep)
      .load(fileLocation)
  }

}
