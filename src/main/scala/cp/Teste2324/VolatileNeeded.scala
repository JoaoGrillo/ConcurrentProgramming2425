package cp.Teste2324
import cp._

object VolatileNeeded extends App {

  @volatile var stop = false // variável de controlo partilhada entre threads

  // Thread 1: corre continuamente até 'stop' ser true
  val t1 = thread {
    var counter = 0
    while (!stop) {
      counter += 1
    }
    println(s"Thread 1 terminou com counter = $counter")
  }

  // Thread 2: espera um pouco e depois altera 'stop' para true
  val t2 = thread {
    Thread.sleep(1000) // espera 1 segundo
    stop = true
    println("Thread 2 alterou stop = true")
  }

  t1.join()
  t2.join()
}

/*
(1) Com @volatile, o programa deve funcionar corretamente:
    - A Thread 1 lê continuamente o valor de stop.
    - A Thread 2 altera o valor de stop para true após 1 segundo.
    - Graças à anotação @volatile, a Thread 1 vê imediatamente essa alteração, sai do while e termina.

(2) Se a variável stop não for marcada como @volatile:
    - Não há garantias de visibilidade entre as duas threads.
    - A Thread 1 pode não ver a alteração feita pela Thread 2.
    - Como consequência, a Thread 1 pode continuar no ciclo while indefinidamente, mesmo que a Thread 2 já tenha alterado stop para true.
    - Isso acontece porque, sem @volatile, o acesso à variável pode não seguir a ordem esperada entre as threads (não há relação happens-before).
*/
