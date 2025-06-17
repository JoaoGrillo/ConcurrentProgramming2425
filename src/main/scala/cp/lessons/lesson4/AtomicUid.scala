package cp.lessons.lesson4
import cp._
import java.util.concurrent.atomic._

object AtomicUid extends App {
    private val uid = new AtomicLong (0L)
    def getUniqueId (): Long =
        uid.incrementAndGet()
    execute {
        log (s"Uid asynchronously: ${ getUniqueId () }")
    }
    log (s"Got a unique id: ${ getUniqueId () }")
}