package cp.lessons.lesson1

object SquareOf5 extends App {
    def square(x: Int): Int = x * x
    val s = square(5)
    println(s"Result: $s")
}