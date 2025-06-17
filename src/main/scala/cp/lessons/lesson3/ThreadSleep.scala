package cp.lessons.lesson3
import cp._

object ThreadsSleep extends App {
    val t = thread {
        Thread.sleep(1000)
        log("New thread running.")
        Thread.sleep(1000)
        log("Still running.")
        Thread.sleep(1000)
        log("Completed.")
    }
    t.join()
    log("New thread joined.")
}