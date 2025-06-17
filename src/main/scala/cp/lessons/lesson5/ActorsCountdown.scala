package cp.lessons.lesson5
import cp._
import akka.actor._
import akka.event.Logging

class CountdownActor extends Actor {
    val log = Logging(context.system , this)
    var n = 10
    def counting: Actor.Receive = {
        case "count" =>
            n -= 1
            log.info(s"n = $n")
            if (n == 0) context.become(done)
    }
    def done = PartialFunction.empty
    def receive = counting
}

object ActorsCountdown extends App {
    val ourSystem = akka.actor.ActorSystem("OurExampleSystem")
    val countdown = ourSystem.actorOf(Props[CountdownActor])
    for (i <- 0 until 20) countdown ! "count"
    Thread.sleep(1000)
    ourSystem.terminate()
}

