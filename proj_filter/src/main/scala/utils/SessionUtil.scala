package utils
import org.apache.spark.sql.SparkSession

class SessionUtil {

  def buildSession(appName: String) : SparkSession = {
    SparkSession.builder
      .appName(appName)
      .getOrCreate
  }
  }

