package infrastructure

import cats.effect.Async
import cats.syntax.applicative._
import fs2.{Pipe, Stream}
import infrastructure.HelloWorldController.CountResponse
import io.circe.generic.auto._
import sttp.capabilities.WebSockets
import sttp.capabilities.fs2.Fs2Streams
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import sttp.tapir.server.ServerEndpoint

import scala.concurrent.duration._

class HelloWorldController[F[_] : Async] extends Controller[F] {
  private def emitCounter: Pipe[F, String, CountResponse] = { _ =>
    Stream
      .iterate(1)(_ + 1)
      .metered[F](1.second)
      .map(CountResponse)
  }

  private def helloWorld: F[String] =
    "Hello world".pure[F]

  private val counterEndpoint =
    endpoint.get
      .in("count")
      .out(webSocketBody[String, CodecFormat.TextPlain, CountResponse, CodecFormat.Json](Fs2Streams[F]))
      .serverLogicSuccess(_ => emitCounter.pure)

  private val helloWorldEndpoint =
    endpoint.get
      .in("hello-world")
      .out(stringBody)
      .serverLogicSuccess(_ => helloWorld)

  override val endpoints: List[ServerEndpoint[Any, F]] = List(helloWorldEndpoint)
  override val webSocketEndpoints: List[ServerEndpoint[Fs2Streams[F] with WebSockets, F]] = List(counterEndpoint)
}

object HelloWorldController {
  case class CountResponse(count: Int)
}