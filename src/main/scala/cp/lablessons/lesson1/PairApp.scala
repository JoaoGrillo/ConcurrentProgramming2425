package cp.lablessons.lesson1
import cp._

case class Pair[A,B](fst: A, snd: B) {
    def +(other:Pair[A,B])(implicit
        numA: Numeric[A],
        numB: Numeric[B]
    ): Pair[A,B] = {
        Pair(numA.plus(fst, other.fst), numB.plus(snd, other.snd))
    }
}

object PairApp extends App {
    val x: Pair[Int, Int] = Pair(1, 2)
    val y = Pair(3, 4)
    val z = x + y

    log(s"x = $x")      // x = Pair(1,2)
    log(s"y = $y")      // y = Pair(3,4)
    log(s"x + y = $z")  // x + y = Pair(4,6)
}
