package infrastructure

import cats.effect.Async
import cats.effect.kernel.Temporal
import cats.syntax.applicative._
import fs2.Stream
import infrastructure.HelloWorldController.CountResponse
import io.circe.generic.auto._
import org.http4s.HttpRoutes
import sttp.capabilities.fs2.Fs2Streams
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import sttp.tapir.server.http4s.Http4sServerInterpreter

import scala.concurrent.duration._

class HelloWorldController[F[_]: Async: Temporal] {
  private val helloWorldEndpoint =
    endpoint.get
      .in("hello-world")
      .out(stringBody)
      .serverLogicSuccess(_ => "Hello world".pure[F])

  private val counterEndpoint =
    endpoint
      .get
      .in("count")
      .out(webSocketBody[String, CodecFormat.TextPlain, CountResponse, CodecFormat.Json](Fs2Streams[F]))
      .serverLogicSuccess { _ =>
        Stream
          .iterate(1)(_ + 1)
          .metered(1.second)
          .pure
      }

  val routes: HttpRoutes[F] = Http4sServerInterpreter().toRoutes(List(helloWorldEndpoint))

  val webSocketRoutes = Http4sServerInterpreter().toWebSocketRoutes(List(counterEndpoint))
}

object HelloWorldController {
  case class CountResponse(count: Int)
}