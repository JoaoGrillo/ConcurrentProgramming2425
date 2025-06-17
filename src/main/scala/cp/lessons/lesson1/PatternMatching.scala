package cp.lessons.lesson1

trait IntOrError
    case class MyInt(i:Int)
        extends IntOrError
    case class MyError(e:String)
        extends IntOrError

object PatternMatching extends App {
    val successors =
        Map(1 -> 2, 2 -> 3, 3 -> 4)
    successors.get(2) match {
        case Some(n) =>
            println(s"Successor is: $n")
        case None    =>
            println("Could not find successor.")
    }

    def show(ie: IntOrError) { 
        ie match {
            case MyInt(i)   =>
                println(s"Number: $i")
            case MyError(e) =>
                println(s"Error: $e")
        }
    }

    show(MyInt(42))       // imprime "Number: 42"
    show(MyError("Oops")) // imprime "Error: Oops"
}