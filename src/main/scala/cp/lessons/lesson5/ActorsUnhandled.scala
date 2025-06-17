package cp.lessons.lesson5
import cp._
import akka.actor._
import akka.event.Logging

class DeafActor extends Actor {
    val log = Logging(context.system, this)
    def receive = PartialFunction.empty // default: ignore and log
    override def unhandled(msg: Any ) = msg match {
        case msg: String => log.info (s"I do not hear '$msg'")
        case msg         => super.unhandled(msg)
    }
}

object ActorsUnhandled extends App {
    val ourSystem = akka.actor.ActorSystem("OurExampleSystem")
    val deafActor : ActorRef =
        ourSystem.actorOf(Props[DeafActor], name = "deafy")
    deafActor ! "ola"
    Thread.sleep (1000)
    deafActor ! 1234
    Thread.sleep (1000)
    ourSystem.terminate()
}
