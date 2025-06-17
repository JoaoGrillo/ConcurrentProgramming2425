package cp.lessons.lesson1

object Collections extends App {
    // Common collection:
    // Seq[T], List[T], Set[M], Map[K,V]
    val msgs_a: Seq[String] = 
        Seq("Hello", "world!")
    val msgs_b: List[String] =
        "Hello"::"world"::Nil
    val msgs_c: Set[String] =
        Set("Hello", "world!")
    val msgs_d: Map[String,Int] =
        Map("Hello"->5, "world!"->6)
    
    println(msgs_a)
    println(msgs_b)
    println(msgs_c)
    println(msgs_d)
}