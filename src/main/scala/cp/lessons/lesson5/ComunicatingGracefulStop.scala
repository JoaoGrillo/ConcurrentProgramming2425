package cp.lessons.lesson5
import cp._
import akka.actor._
import akka.event.Logging
import akka.pattern.gracefulStop
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Success, Failure}

class GracefulPingy extends Actor {
    val log = Logging(context.system, this)
    val pongy = context.actorOf(Props[Pongy], "pongy")
    context.watch(pongy)
    def receive = {
        case "start" => pongy ! "ping"
        case "pong" => log.info ("Got a pong")
        case "Die, Pingy!" =>
        context.stop (pongy)
        case Terminated(pongy) =>
        context.stop(self)
    } 
}

class Pongy extends Actor {
    val log = Logging(context.system, this)
    def receive = {
        case "ping" =>
            log.info ("Got a ping -- ponging back!")
            sender ! "pong"
    }
    override def postStop () =
        log.info ("pongy going down")
}

object CommunicatingGracefulStop extends App {
    val ourSystem = akka.actor.ActorSystem("OurExampleSystem")
    val gracePingy = ourSystem.actorOf(Props[GracefulPingy], "gracePingy")
    gracePingy ! "start"
    val stopped = gracefulStop(gracePingy, 3.seconds, "Die, Pingy!")
    stopped onComplete { // stopped is a Future ( not covered )
        case Success(x) =>
            log ("graceful shutdown successful")
            ourSystem.terminate()
        case Failure(t) =>
            log ("grace not stopped!")
            ourSystem.terminate()
    } 
}

