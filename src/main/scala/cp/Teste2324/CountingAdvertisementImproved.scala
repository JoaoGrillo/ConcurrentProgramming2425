package cp.Teste2324
import cp._
import java.util.concurrent.atomic.AtomicInteger

object CountingAdvertisementImproved extends App {
    class Advertisement(val name: String) {
        private val views = new AtomicInteger(0)
        def +=(more: Int): Unit = views.addAndGet(more)
        override def toString(): String = s"$name: ${views.get()} views"
    }

    def atomicIncr1(ad: Advertisement, more: Int): Unit = {
        ad += more
    }


    def atomicIncr2(ad1: Advertisement, more1: Int, 
                    ad2: Advertisement, more2: Int): Unit = {
        if (ad1.name < ad2.name)
            ad1.synchronized {
                ad2.synchronized {
                    ad1 += more1
                    ad2 += more2
                }
            }
        else
            ad2.synchronized {
                ad1.synchronized {
                    ad1 += more1
                    ad2 += more2
                }
            }
        }


    // Testing the execution
    val crush = new Advertisement("Candy Crush")
    val uEats = new Advertisement("Uber Eats")
    val temu = new Advertisement("Temu")

    execute {
        atomicIncr2(crush, 4, temu, 4)
    }
    execute {
        atomicIncr1(uEats, 4)
    }
    execute {
        atomicIncr2(temu, 2, crush, 5)
    }
    execute {
        atomicIncr2(temu, 6, uEats, 7)
    }

    Thread.sleep(1000)
    println(s"$crush, $uEats, $temu")
}

/*
3.1. 
A execução pretende incrementar contadores de visualizações de forma concorrente.
O código tem risco de deadlock em atomicIncr2, pois adquire dois locks (ad1 e ad2) em ordem variável. 
Para evitar isso, basta garantir que os locks sejam sempre adquiridos na mesma ordem. 
Como os nomes são únicos, podemos usá-los:
    def atomicIncr2(ad1: Advertisement, more1: Int, 
                    ad2: Advertisement, more2: Int): Unit = {
        if (ad1.name < ad2.name)
            ad1.synchronized {
                ad2.synchronized {
                    ad1 += more1
                    ad2 += more2
                }
            }
        else
            ad2.synchronized {
                ad1.synchronized {
                    ad1 += more1
                    ad2 += more2
                }
            }
        }
Corrigimos também o println, que precisa de interpolação:
    println(s"$crush, $uEats, $temu")

3.2.
Para evitar o uso de synchronized, usamos AtomicInteger:
    import java.util.concurrent.atomic.AtomicInteger
    class Advertisement(val name: String) {
            private val views = new AtomicInteger(0)
            def +=(more: Int): Unit = views.addAndGet(more)
            override def toString(): String = s"$name: ${views.get()} views"
        }

        def atomicIncr1(ad: Advertisement, more: Int): Unit = {
            ad += more
        }
*/