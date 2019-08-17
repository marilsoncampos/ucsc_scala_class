
import org.backuity.clist._
import scala.math.random
import org.apache.spark._


object MainApp {
    def execRun(obj : Run): Unit = {
        val etlDate = obj.dtDate
        val daysBack = obj.daysBack
        val debug = obj.debug
        println(s"Running ETL for $etlDate and $daysBack days back.")
        println(s"Debug: $debug")
    }

    def execShow(obj : Show): Unit = {
        val daysBack = obj.daysBack
        println(s"Show command")
        println(s"Days back: $daysBack")
    }

    def runJob(args: Array[String]): Unit = {
        val conf = new SparkConf().setAppName("Spark Pi")
        val spark = new SparkContext(conf)
        val slices = if (args.length > 0) args(0).toInt else 2
        val n = math.min(100000L * slices, Int.MaxValue).toInt // avoid overflow
        val count = spark.parallelize(1 until n, slices).map { i =>
            val x = random * 2 - 1
            val y = random * 2 - 1
            if (x*x + y*y < 1) 1 else 0
        }.reduce(_ + _)
        println("Pi is roughly " + 4.0 * count / n)
        spark.stop()
    }
    def main(args: Array[String]): Unit = {
        runJob(args)
        //Cli.parse(args).withCommands(new Run, new Show) match {
        //   case Some(x: Run)  => execRun(x)
        //   case Some(x: Show) => execShow(x)
        //   case None => println("Error.")
        //}
    }
} 