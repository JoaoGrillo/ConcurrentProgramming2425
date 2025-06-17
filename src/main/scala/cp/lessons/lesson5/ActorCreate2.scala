package cp.lessons.lesson5
import cp._
import akka.actor._
import akka.event.Logging

object ActorCreate2 extends App {
    lazy val ourSystem = akka.actor.ActorSystem("OurExampleSystem")

    class Hi(val hi: String) extends Actor {
        val log = Logging(context.system, this)
        def receive = {
            case "hi" =>
                log.info(s"Got a '$hi'... $hi!")
            case msg  =>
                log.info(s"Unexpected '$msg'")
                context.stop(self)
        }
    }

    val hiActor: ActorRef =
        ourSystem.actorOf(Props(new Hi("ola")), name = "greeter")
    hiActor ! "hi"     // ... Gor a 'ola'... ola!
    Thread.sleep(1000)
    hiActor ! "yo"      // ... Unexpected 'yo'
    Thread.sleep(1000)
    hiActor ! "привет"  // ... Message (...) was not delivered.
    Thread.sleep(1000)
    ourSystem.terminate()   // Encerra o sistema de atores
}


