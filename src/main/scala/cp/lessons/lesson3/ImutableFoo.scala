package cp.lessons.lesson3
import cp._

class Foo(final val a: Int, val b: Int, c: Int)
/* Encoding as Java:
class Foo {
  final private int a$;
  final private int b$;
  final private int c$;
  final public int a()
    { return a$; }
  public int b()
    { return b$; }
  public Foo(int a,
             int b,
             int c) {
    { a$ = a; b$ = b; c$ = c; }
  }
}
*/
object ImutableFoo extends App {
  val foo = new Foo(10, 20, 30)

  // Mostramos os valores imutáveis
  log(s"foo.a = ${foo.a}, foo.b = ${foo.b}")

  // foo.c não pode ser acedido diretamente porque não é val nem var
  // foo.a e foo.b são imutáveis (val), logo não podem ser modificados
}
