package cp.lessons.lesson3
import cp._
import scala.collection._

object SynchronizedPool extends App {
    // our set of tasks
    private val tasks = mutable.Queue[()=>Unit]()

    object Worker extends Thread {
        setDaemon(true)
        def poll() = tasks.synchronized {
            while (tasks.isEmpty) tasks.wait()  // now using wait
            tasks.dequeue()
        }
        override def run() = while (true) {
            val task = poll()
            task()
        }
    }

    Worker.start()

    def asynchr(body: =>Unit) = tasks.synchronized {
        tasks.enqueue(()=>body) // now notifying
        tasks.notify()
    }

    asynchr{ log("Hello") }
    asynchr{ log(" world!") }
    Thread.sleep(500)
}