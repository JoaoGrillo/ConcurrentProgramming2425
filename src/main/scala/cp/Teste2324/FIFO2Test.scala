package cp.Teste2324
import cp._

object FIFO2Test extends App {
    val queue = new FIFO2[Int]()

    // Thread que insere 5 elementos na fila
    val producer = thread {
        for (i <- 1 to 5) {
            log(s"Producer tentando enfileirar $i")
            queue.blockingEnqueue(i)
            log(s"Producer enfileirou $i")
            Thread.sleep(500)   // simula trabalho
        }
    }

    // Consumidores
    val consumer1 = thread {
        for (_ <- 1 to 3) {
            val elem = queue.blockingDequeue()
            log(s"Consumer 1 retirou $elem")
            Thread.sleep(800)
        }
    }

    val consumer2 = thread {
        for (_ <- 1 to 2) {
            val elem = queue.blockingDequeue()
            log(s"Consumer 2 retirou $elem")
            Thread.sleep(700)
        }
    }

    producer.join()
    consumer1.join()
    consumer2.join()

    log("Fim do teste da FIFO2")
}