package cp.lessons.lesson1

object Byname extends App {
    def runTwice(body: =>Unit) = {
        body
        body
    }

    runTwice {  // prints "Hello" twice
        println("Hello")
    }
}