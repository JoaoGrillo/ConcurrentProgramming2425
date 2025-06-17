package cp.lessons.lesson3
import cp._

object ThreadsNondeterminism extends App {
    val t = thread {
        log("New thread running.")
    }

    log("...")
    log("...")
    t.join()      // Com esta linha comentada, o código torna-se não determinístico
    log("New thread joined.")
}