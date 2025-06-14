package cp.lessons.lesson1

// A e B são tipos genéricos
class Pair[A,B](val fst: A, val snd: B) 

object PairApp extends App {
    val x: Pair[Int,String] =
        new Pair(4,"a")
    val y = new Pair(2,5)   // infer type 

    println(s"x = (${x.fst}, ${x.snd})")
    println(s"x = (${y.fst}, ${y.snd})")
}