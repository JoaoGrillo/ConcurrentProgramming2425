package cp.lablessons.lesson1
import cp._

object Check extends App {
    // Definição da função de fuse com for-comprehension
    def check[T](xs: Seq[T])(pred: T => Boolean): Boolean = {
        xs.forall { x =>
            try {
                pred(x)
            } catch {
                case _: Throwable => false
            }
        }
    }
    
    // Teste
    val result = check(1 until 10)(40 / _ > 0)
    log(s"check result: $result")   // Deve imprimir false porque 40/0 lança exceção
}

