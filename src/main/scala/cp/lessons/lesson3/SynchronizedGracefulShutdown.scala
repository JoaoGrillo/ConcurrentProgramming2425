package cp.lessons.lesson3

import cp._
import scala.collection.mutable
import scala.annotation.tailrec

object SynchronizedGracefulShutdown extends App {

  private val tasks = mutable.Queue[() => Unit]()

  object Worker extends Thread {
    // flag de término
    var terminated = false

    // método poll com verificação de término
    def poll(): Option[() => Unit] = tasks.synchronized {
      while (tasks.isEmpty && !terminated) tasks.wait()
      if (!terminated) Some(tasks.dequeue()) else None
    }

    @tailrec
    override def run(): Unit = poll() match {
      case Some(task) => task(); run()
      case None => // término gracioso
    }

    // método para pedir término do worker
    def shutdown(): Unit = tasks.synchronized {
      terminated = true
      tasks.notify()
    }
  }

  // inicia o worker
  Worker.setDaemon(true)
  Worker.start()

  // adiciona tarefas
  def asynchr(body: => Unit): Unit = tasks.synchronized {
    tasks.enqueue(() => body)
    tasks.notify()
  }

  asynchr { log("Hello") }
  asynchr { log("world!") }

  // aguarda um pouco e encerra o worker
  Thread.sleep(500)
  Worker.shutdown()
  Worker.join() // espera o término da thread
}
