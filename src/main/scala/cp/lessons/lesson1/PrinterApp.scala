package cp.lessons.lesson1

class Printer(val greeting : String ) {
    def printMessage(): Unit =
        println(greeting + "!")
    def printNumber(x: Int ): Unit = {
        println("Number: " + x )
    }
}

object PrinterApp extends App {
    val printy = new Printer("Hi")
        // instatiate
    printy.printMessage() // prints "Hi!"
    printy.printNumber(5) // prints "Number: 5"
}