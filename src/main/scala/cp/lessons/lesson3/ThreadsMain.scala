package cp.lessons.lesson3

object ThreadsMain extends App {
    val t: Thread =
        Thread.currentThread
    val name = t.getName
    println(s"I am the thread $name")
}