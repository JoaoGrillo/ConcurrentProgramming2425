package cp.lessons.lesson5
import cp._
import akka.actor._
import akka.event.Logging

class HelloActor(val hello: String) extends Actor {
    val log = Logging(context.system, this)
    def receive = { // Any => Unit (partial)
        case "hello" =>
            log.info(s"Received a '$hello'... $hello!")
        case msg     =>
            log.info(s"Unexpected message '$msg'")
            context.stop(self)
    }
}

object HelloActor { // companion
    // two factory methods below
    def props(hello: String) =
        Props(new HelloActor(hello))
    def propsAlt(hello: String) =
        Props(classOf[HelloActor], hello)
    // def propsAlt2 = Props[HelloActor]
}


object ActorCreate extends App {
    lazy val ourSystem = akka.actor.ActorSystem("OurExampleSystem")

    val hiActor: ActorRef =
        ourSystem.actorOf(HelloActor.props("ola"), name = "greeter")
    hiActor ! "hello"     // Envia "olá" para o ator
    Thread.sleep(1000)
    hiActor ! "hi"      // Envia "hi" para o ator
    Thread.sleep(1000)
    ourSystem.terminate()   // Encerra o sistema de atores
}