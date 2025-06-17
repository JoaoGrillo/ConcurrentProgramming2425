package cp.lessons.lesson1

object For extends App {
    for (i <- 0 until 10) println(i)
    // equivalent to
    0.until(10).foreach(i => println(i))

    val negatives_a =
        for (i <- 0 until 10) yield -i
    val negatives_b =
        (0 until 10).map(i => -i)

    println(negatives_a)
    println(negatives_b)

    val pairs_a =
        for (x <- 0 until 4;
             y <- 0 until 4) yield (x,y)
    val pairs_b =
        (0 until 4).flatMap(x =>
            (0 until 4).map(y =>
                (x, y)))

    println(pairs_a)
    println(pairs_b)
}