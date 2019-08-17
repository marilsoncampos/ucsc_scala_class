
import org.backuity.clist.{Command, opt, args}

class Show extends Command(description = "Show the parameters of ETL job") {
    var daysBack = opt[Int](
        default = 1, 
        description = "Number of days back to include in processing")
}