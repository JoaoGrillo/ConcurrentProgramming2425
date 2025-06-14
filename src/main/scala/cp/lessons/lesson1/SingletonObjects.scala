package cp.lessons.lesson1

object Test {
    val Pi = 3.14
}

object SingletonObjects extends App {
    val area = Test.Pi * 5 * 5
    println(s"Area of circle with radius 5: $area")
}
