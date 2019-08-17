package utils

class CliParser() {

    type OptionMap = Map[Symbol, Any]

    def nextOption(map : OptionMap, list: List[String]) : OptionMap = {
      list match {
        case Nil => map
        case "--input" :: value :: tail =>
                nextOption(map ++ Map('input -> value), tail)
        case "--output" :: value :: tail =>
                nextOption(map ++ Map('output -> value), tail)
        case option :: _ =>
          println("Unknown option " + option)
          Map()
      }
    }

    def printUsage(): Unit = {
      println("Usage: <program> --input <input_path> --output <output_path>")
    }

    def parse(args: Array[String]) : OptionMap = {
        if (args.length != 4) {
            printUsage()
            Map()
        } else {
            nextOption(Map(), args.toList)
        }     
    }
}
