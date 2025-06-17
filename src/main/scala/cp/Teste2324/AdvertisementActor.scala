package cp.Teste2324
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

object AdvertisementActor extends App {
    var sentAds = 0
    class AppMgr extends Actor {
        def receive = {
            case ("start",counter:Actor) =>
                val a1 = context.actorOf(Props(new
                    MyApp(1)), "MyApp1")
                val a2 = context.actorOf(Props(new
                    MyApp(2)), "MyApp2")
                a1 ! ("start",sender)
                a2 ! ("start",sender)
        }
    }
    class MyApp(id:Int) extends Actor {
        def receive = {
            case ("start",counter:ActorRef) =>
                // simulate the sending of ads
                if (id==1) {
                    counter ! ("moreAdds","Candy Crush",6)
                    sentAds += 6
                    Thread.sleep(2000) // wait 2 seconds
                    counter ! ("moreAdds","Temu",2)
                    sentAds += 2
                } else {
                    counter ! ("moreAdds","Candy Crush",3)
                    sentAds += 3
                    Thread.sleep(2000) // wait 2 seconds
                    counter ! ("moreAdds","Uber Eats",5)
                    sentAds += 5
                }
        }
    }

    class AdCounter extends Actor {
        var receivedAds = 0
        def receive = {
            case "start" =>
                val appMgr = sys.actorOf(Props[AppMgr], "AppMgr")
                appMgr ! ("start",this)

            case ("moreAdds",prod:String,views:Int) =>
                receivedAds += views
                println(s"Got $views more adds from '$prod' (total: $receivedAds)")
                // throw exception if more adds received than sent
                assert(sentAds >= receivedAds)

            case "explode" =>
                throw new RuntimeException("Exploded.")
        }
    }
    
    // Running the system
    val sys = akka.actor.ActorSystem("AdCountingSystem")
    val counter = sys.actorOf(Props[AdCounter], "AdCounter")
    counter ! "start"
    Thread.sleep(1000) // wait 1 second
    //val checker = sys.actorOf(Props[CheckActor], "checker")
    //checker ! "../*"            // finds the checker and its siblings
    //checker ! "/user/AppMgr/*" 
    //Thread.sleep(10000)
    counter ! "bum"
    counter ! "explode"
    Thread.sleep(3000)
    sys.terminate()
}