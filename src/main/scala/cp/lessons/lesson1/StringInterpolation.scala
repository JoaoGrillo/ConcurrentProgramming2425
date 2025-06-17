package cp.lessons.lesson1

object StringInterpolation extends App {
    val number = 7
    val msg =
        s"After $number comes ${number+1}!"
    println(msg)
}