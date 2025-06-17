import scala.concurrent._
package object cp {
    def log(msg: String): Unit =
        println(
            s"${Thread.currentThread.getName}: $msg"
        )
    
    def thread(body: =>Unit): Thread = {
        val t = new Thread {
            override def run() = body
        }
        t.start()
        t
    }
    def execute(body: =>Unit) =
        ExecutionContext.global.execute(
            new Runnable { def run() = body }
        )
}