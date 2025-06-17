package cp.lessons.lesson5
import cp._
import akka.actor._
import akka.event.Logging

class ChildActor extends Actor {
    val log = Logging(context.system , this)
    def receive = {
        case "sayhi" =>
            val parent = context.parent
            log.info(s"my parent $parent made me say hi!")
    }
    override def postStop() {
        log.info("child stopped!")
    }
}

class ParentActor extends Actor {
    val log = Logging (context.system, this)
    def receive = {
        case "create" =>
            context.actorOf(Props[ChildActor])
            log.info (s"created a kid; \nchildren = ${context.children}")
        case "sayhi" =>
            log.info ("Kids, say hi!")
            for (c <- context.children)
                c ! "sayhi"
        case "stop" =>
            log.info ("parent stopping")
            context.stop(self)
    }
}

object ActorsHierarchy extends App {
    val ourSystem = akka.actor.ActorSystem("OurExampleSystem")
    val parent = ourSystem.actorOf(Props[ParentActor], "parent")
    parent ! "create"
    parent ! "create"
    Thread.sleep(1000)
    parent ! "sayhi"
    Thread.sleep(1000)
    parent ! "stop"
    Thread.sleep(1000)
    ourSystem.terminate()
}
