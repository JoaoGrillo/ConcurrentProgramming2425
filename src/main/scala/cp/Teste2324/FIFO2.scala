package cp.Teste2324
import cp._

class FIFO2[T] {
    // define the state of the queue
    private val queue = scala.collection.mutable.Queue[T]()

    def blockingEnqueue(elem: T): Unit = {
        // implement enqueuing
        queue.synchronized {
            while (queue.size >= 2) {
                queue.wait()
            }
            queue.enqueue(elem)
            queue.notifyAll()
        }
    }

    def blockingDequeue(): T = {
        // implement dequeuing
        queue.synchronized {
            while (queue.isEmpty) {
                queue.wait()
            }
            val elem = queue.dequeue()
            queue.notifyAll()
            elem
        }
    }
}
