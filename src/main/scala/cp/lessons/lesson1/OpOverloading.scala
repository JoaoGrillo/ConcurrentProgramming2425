package cp.lessons.lesson1

class Position(val x: Int, val y: Int) {
    def +(that: Position) =
        new Position(x + that.x, y + that.y)
    def *(n: Int) =
        new Position(x * n, y * n)
    override def toString: String = s"($x, $y)"
}

object OpOverloading extends App {
    // Using operators
    val p1 = new Position(3,4)
    val p2 = p1 + p1 * 2 //?

    println(p1)
    println(p2)
}