package infrastructure

import cats.effect.Async
import cats.syntax.applicative._
import sttp.tapir._
import sttp.tapir.server.ServerEndpoint

class HelloWorldController[F[_] : Async] {
  private val helloWorldEndpoint =
    endpoint.get
      .in("hello-world")
      .out(stringBody)
      .serverLogicSuccess(_ => "Hello world".pure[F])

  val all: List[ServerEndpoint[Any, F]] = List(helloWorldEndpoint)
}
