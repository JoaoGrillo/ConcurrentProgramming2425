package cp.lessons.lesson3
import cp._

object SynchronizedDeadlock extends App {
    import SynchronizedNesting.Account
    def send(a: Account, b: Account, n: Int) = a.synchronized {
        b.synchronized {
            a.money -= n
            b.money += n
        }
    }

    val l = new Account("Lucy", 1000)
    val j = new Account("Jim", 2000)
    val t1 = thread { for (i<- 0 until 100) send(l, j, 1) }
    val t2 = thread { for (i<- 0 until 100) send(j, l, 1) }
    t1.join(); t2.join()
    log(s"a = ${l.money}, b = ${l.money}")
}