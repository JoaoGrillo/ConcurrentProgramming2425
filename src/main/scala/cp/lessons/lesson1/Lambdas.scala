package cp.lessons.lesson1

object Lambdas extends App {
    val twice_a: Int=>Int = (x:Int) => x*2
    val twice_b = (x:Int) => x*2    // same as a but type is inferred
    val twice_c: Int=>Int = x => x*2
    val twice_d: Int=>Int = _ * 2

    val a = twice_a(4)
    val b = twice_b(4)
    val c = twice_c(4)
    val d = twice_d(4)

    println(s"a = $a, b = $b, c = $c e d = $d")
}