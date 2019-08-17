
import org.backuity.clist.{Command, opt, args}

class Run extends Command(description = "Runs the ETL job") {
    var dtDate         = opt[String](default="Today", description = "Date in the format YYYY-MM-DD")
    var daysBack = opt[Int](default = 1, 
                            description = "Number of days back to include in processing")
    var debug = opt[Boolean](description = "Show debug info")
}