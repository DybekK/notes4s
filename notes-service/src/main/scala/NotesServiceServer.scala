import cats.effect._
import cats.syntax.applicative._
import cats.syntax.functor._
import cats.syntax.flatMap._
import infrastructure.HelloWorldController
import org.http4s.HttpRoutes
import org.http4s.blaze.server.BlazeServerBuilder
import org.http4s.server.Router
import org.http4s.server.websocket.WebSocketBuilder2
import sttp.capabilities.WebSockets
import sttp.capabilities.fs2.Fs2Streams
import sttp.tapir.server.ServerEndpoint
import sttp.tapir.server.http4s.Http4sServerInterpreter
import sttp.tapir.swagger.bundle.SwaggerInterpreter

import scala.concurrent.ExecutionContext

object NotesServiceServer extends IOApp {
  implicit val ec: ExecutionContext = scala.concurrent.ExecutionContext.Implicits.global

  private val Port = 8080
  private val Host = "localhost"
  private val Title = "Notes Service API"
  private val Version = "0.1"

  override def run(args: List[String]): IO[ExitCode] =
    runServer[IO]()

  private def runServer[F[_]: Async](): F[ExitCode] = {
    val async = implicitly[Async[F]]

    for {
      _ <- println("Starting server...").pure

      // routes
      helloWorldRoutes = new HelloWorldController().all
      routes = toRoutes(helloWorldRoutes)

      // server
      _ <- BlazeServerBuilder[F]
        .withExecutionContext(ec)
        .bindHttp(Port, Host)
        .withHttpApp(Router("/" -> routes).orNotFound)
        .resource
        .use(_ => async.never[Unit])
    } yield ExitCode.Success
  }

  private def toRoutes[F[_] : Async](logicRoutes: List[ServerEndpoint[Any, F]]): HttpRoutes[F] = {
    val routes = logicRoutes ++ SwaggerInterpreter().fromServerEndpoints(logicRoutes, Title, Version)
    Http4sServerInterpreter().toRoutes(routes)
  }

  private def toWebSocketRoutes[F[_] : Async](logicRoutes: List[ServerEndpoint[Fs2Streams[F] with WebSockets, F]]): WebSocketBuilder2[F] => HttpRoutes[F] =
    Http4sServerInterpreter().toWebSocketRoutes(logicRoutes)
}