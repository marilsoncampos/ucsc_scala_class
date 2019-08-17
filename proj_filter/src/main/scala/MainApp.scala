import utils.{CliParser, CsvReader, SessionUtil}

object MainApp {

    def main(args: Array[String]): Unit = {
        // Extract parameters
        val parser =  new CliParser()
        val params = parser.parse(args)
        if (params.isEmpty) {
            parser.printUsage()
            return
        }
        val inputPath: String = params.getOrElse('input, "").toString
        val outputPath: String = params.getOrElse('output, "").toString
        if (inputPath == "" || outputPath == "") {
            parser.printUsage()
            return
        }
        println("Job Parameters")
        println("  - Input path  : " + inputPath)
        println("  - Output path : " + outputPath)
        // Prepared to launch the Job
        val appName = "Lookup join Job"
        val spark = new SessionUtil().buildSession(appName)
        val csvReader = new CsvReader(spark)
        val ratingsDf = csvReader.loadFile(inputPath)
        ratingsDf.createOrReplaceTempView("df")
        val p4Sql = """
          SELECT
            userId, bround(AVG(rating), 3) AS average
          FROM df
          GROUP BY userId
          ORDER BY average DESC
          LIMIT 10
        """
        val top10Positive = spark.sql(p4Sql)
        top10Positive.write.csv(outputPath)
    }
} 