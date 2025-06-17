package cp.Teste2324
import cp._

object CountingAdvertisement extends App {
    class Advertisement(name: String) {
        private var views: Int = 0
        def +=(more: Int) = views += more
        override def toString(): String =
            s"$name: $views views"
    }

    def atomicIncr1(ad: Advertisement, more: Int) {
        ad.synchronized { ad += more }
    }

    def atomicIncr2(ad1: Advertisement, more1: Int,
                    ad2: Advertisement, more2: Int) {
        ad1.synchronized {
            ad2.synchronized {
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