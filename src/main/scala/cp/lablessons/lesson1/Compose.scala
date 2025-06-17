package cp.lablessons.lesson1
import cp._

object Compose extends App {
    // Definição da função de composição
    def compose[A, B, C](g: B => C, f: A => B): A => C =
        (a: A) => g(f(a))

    // Funções de exemplo
    val double: Int => Int = x => x*2
    val toStr: Int => String = x => s"Result: $x"

    // Composição: toStr(double(x))
    val composed: Int => String = compose(toStr, double)

    // Teste
    log(composed(5)) // Deve imprimir "Result: 10"
}

