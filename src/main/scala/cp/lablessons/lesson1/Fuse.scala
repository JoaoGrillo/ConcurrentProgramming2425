package cp.lablessons.lesson1
import cp._

object Fuse extends App {
    // Definição da função de fuse com for-comprehension
    def fuse1[A, B](a: Option[A], b: Option[B]): Option[(A, B)] =
        for {
            av <- a
            bv <- b
        } yield (av, bv)
    
    // Definição da função de fuse com pattern matching
    def fuse2[A, B](a: Option[A], b: Option[B]): Option[(A, B)] =
        (a,b) match {
            case (Some(av), Some(bv)) => Some((av,bv))
            case _ => None
        }

    // Testes
    log(fuse1(Some(1), Some("x")).toString)     // Deve imprimir Some((1,x))
    log(fuse1(Some(1), None).toString)          // Deve imprimir None
    log(fuse1(None, Some("x")).toString)        // Deve imprimir None

    log(fuse2(Some(2), Some("y")).toString)     // Deve imprimir Some((2,y))
    log(fuse2(None, None).toString)             // Deve imprimir None
}

