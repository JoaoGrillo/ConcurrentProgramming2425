package cp.lessons.lesson1

trait Logging {     // Similar to Java Interfaces
    def log(s: String): Unit    // just declared (abstract)
    def warn(s: String) = log("Warn: " + s)
    def error(s: String) = log("Error: " + s)
}

class PrintLogging extends Logging {
    def log(s: String) = println(s)
}

object LoggingApp extends App {
    val x = new PrintLogging
    val y = new Logging {
        def log(s:String) : Unit =
            println(s)
    }

    x.warn("This is a warning from x")      // usa println
    x.error("This is an error from x")      // usa println

    y.warn("This is a warning from y")      // usa println
    y.error("This is an error from y")      // usa println
}