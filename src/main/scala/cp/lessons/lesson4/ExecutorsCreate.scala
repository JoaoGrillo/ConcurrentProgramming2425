package cp.lessons.lesson4
import cp._
import scala.concurrent._
import java.util.concurrent.ForkJoinPool

object ExecutorsCreate extends App {
    val executor = new ForkJoinPool
    executor.execute ( new Runnable {
        def run() = log("This task is running asynchronously.")
    })
    Thread.sleep (500) // not needed with fork := false in SBT
}
