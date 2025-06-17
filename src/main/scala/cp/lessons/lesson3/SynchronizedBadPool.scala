package cp.lessons.lesson3
import cp._
import scala.collection._

object SynchronizedBadPool extends App {
    // our set of tasks
    private val tasks = mutable.Queue[()=>Unit]()

    // our single working thread
    val worker = new Thread {
        def poll(): Option[()=>Unit] =
            tasks.synchronized {
                if (tasks.nonEmpty) Some(tasks.dequeue())
                else                None
            }
        // keep on trying to run forever!
        override def run() = while (true)
            poll() match {
                case Some(task) => task()
                case None =>
            }
    }

    // starting the worker as a daemon
    worker.setName("Worker")
    worker.setDaemon(true)
    worker.start()

    // Test our program
    def asynchr(body: =>Unit) =
        tasks.synchronized {
            tasks.enqueue(()=>body)
        }
    
    asynchr{ log("Hello") }
    asynchr{ log(" world!") }
    Thread.sleep(5000)
}