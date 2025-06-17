package cp.lessons.lesson1

object TryCatch extends App {
    def getInt(ie:IntOrError): Int = {
        ie match {
            case MyInt(i)   => i
            case MyError(e) => throw new Exception(e)
            // (match error if an error is found)
        }
    }

    // println(getInt(MyInt(100)))           // Deve imprimir 100
    // println(getInt(MyError("Falhou")))    // Vai lançar exceção e interromper o programa


    def showInt(ie:IntOrError) = 
        try {
            println(s"Int value: ${getInt(ie)}")
        } catch {
            case _: Throwable =>
                println("Got some error - probably not an int.")
        } finally {
            // always executes
            // (for side-effects, not to return results)
            println("Done showing")
        }
    
    showInt(MyInt(42))          // Deve imprimir o valor
    showInt(MyError("Oops"))    // Deve imprimir mensagem de error
}