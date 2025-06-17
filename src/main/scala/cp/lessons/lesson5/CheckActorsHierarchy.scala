package cp.lessons.lesson5
import cp._
import akka.actor._
import akka.event.Logging

class CheckActor extends Actor {
    val log = Logging(context.system, this )
    def receive = {
        case path: String =>
            log.info (s"checking path $path")
            context.actorSelection(path) ! Identify(path)
        case ActorIdentity(path, Some(ref)) =>
            log.info (s"found actor $ref at $path")
        case ActorIdentity(path, None) =>
            log.info (s"could not find an actor at $path")
    }
} // Discovery: actorSelection + Identify + Actor Identity

object CheckActorsHierarchy extends App {
    val ourSystem = akka.actor.ActorSystem("OurExampleSystem")
    val checker = ourSystem.actorOf(Props[CheckActor], "checker")
    checker ! "../*"            // finds the checker and its siblings
    checker ! "../../*"         // finds user and system guardians
    checker ! "/system/*"       // finds internal actors
    checker ! "/user/checker2"  // logs that no actors were found
    ourSystem.terminate()
}
