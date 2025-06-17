package cp.lessons.lesson4
import cp._
import scala.concurrent._
import java.util.concurrent.ForkJoinPool

object ExecutorsContextCreate extends App {
    val pool = new forkjoin.ForkJoinPool(2)
    val ectx = ExecutionContext.fromExecutorService(pool)
    ectx.execute ( new Runnable {
        def run() = log("Running on the execution context again.")
    })
    Thread.sleep (500) // not needed with fork := false in SBT
}
