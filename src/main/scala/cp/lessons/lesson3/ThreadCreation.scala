package cp.lessons.lesson3

object ThreadsCreation extends App {
    class MyThread extends Thread {
        override def run(): Unit = {
            println("New thread running.")
        }
    }

    val t = new MyThread        // Cria a thread (mas ainda não executa)
    t.start()                   // Solicita que a JVM execute run() numa nova thread
    t.join()                    // Espera a thread terminar antes de continuar
    println("New thread joined.")   // Executando só depois que a thread t termina
}