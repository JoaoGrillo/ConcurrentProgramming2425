package cp.lessons.lesson3
import cp._
import scala.collection._

object SynchronizedGuardedBlocks extends App {
    val lock = new AnyRef
    var message: Option[String] = None
    val greeter = thread {
        lock.synchronized {
            while (message == None) lock.wait() // non-busy waiting for a message
            log(message.get)                    // it will eventually log!
        }
    }
    lock.synchronized {
        message = Some("Hello!")
        lock.notify()                   // awakes the (possibly) locked thread
    }
    greeter.join()
}