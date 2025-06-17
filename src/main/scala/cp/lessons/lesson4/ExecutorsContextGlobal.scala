package cp.lessons.lesson4
import cp._
import scala.concurrent._

object ExecutorsContextGlobal extends App {
    val ectx = ExecutionContext.global
    ectx.execute ( new Runnable {
        def run() = log("Running on the execution context.")
    })
    Thread.sleep (500) // not needed with fork := false in SBT
}
