package cp.lessons.lesson4
import cp._

object ExecutorsContextSleep extends App {
    for (i<- 0 until 32) execute {
        Thread.sleep(2000)
        log(s"Task $i completed.")
    }
    Thread.sleep (10000) // not needed with fork := false in SBT
}
