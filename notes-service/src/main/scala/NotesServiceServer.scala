import cats.effect.{Async, IO, IOApp, Resource}
import cats.syntax.all._
import infrastructure.{Controller, HelloWorldController}
import org.http4s.HttpRoutes
import org.http4s.blaze.server.BlazeServerBuilder
import org.http4s.server.websocket.WebSocketBuilder2
import org.http4s.server.{Router, Server}
import sttp.tapir.server.http4s.Http4sServerInterpreter
import sttp.tapir.swagger.bundle.SwaggerInterpreter

import scala.concurrent.ExecutionContext

object NotesServiceServer extends IOApp.Simple {
  def run: IO[Unit] = {
    val ec = ExecutionContext.global

    val controller = new HelloWorldController[IO]

    val httpRoutes = toRoutes(controller)
    val webSocketRoutes = toWebSocketRoutes(controller)

    runServers(ec, httpRoutes, webSocketRoutes)
  }

  private def createHttpServer[F[_] : Async](ec: ExecutionContext, routes: HttpRoutes[F]): Resource[F, Server] =
    BlazeServerBuilder[F]
      .withExecutionContext(ec)
      .bindHttp(8080, "localhost")
      .withHttpApp(Router("/" -> routes).orNotFound)
      .resource

  private def createWebSocketServer[F[_] : Async](ec: ExecutionContext, webSocketRoutes: WebSocketBuilder2[F] => HttpRoutes[F]): Resource[F, Server] =
    BlazeServerBuilder[F]
      .withExecutionContext(ec)
      .bindHttp(8081, "localhost")
      .withHttpWebSocketApp(wsb => Router("/" -> webSocketRoutes(wsb)).orNotFound)
      .resource

  private def runServers[F[_] : Async](ec: ExecutionContext, httpRoutes: HttpRoutes[F], webSocketRoutes: WebSocketBuilder2[F] => HttpRoutes[F]): F[Unit] = {
    val httpServerResource = createHttpServer(ec, httpRoutes)
    val webSocketServerResource = createWebSocketServer(ec, webSocketRoutes)

    (httpServerResource, webSocketServerResource).tupled.use { case (_, _) =>
      Async[F].never
    }
  }

  private def toRoutes[F[_] : Async](controllers: Controller[F]*): HttpRoutes[F] = {
    val endpoints = controllers.flatMap(_.endpoints).toList

    val endpointsWithSwagger = endpoints ++ SwaggerInterpreter().fromServerEndpoints(endpoints, "Notes service", "1.0")
    Http4sServerInterpreter().toRoutes(endpointsWithSwagger)
  }

  private def toWebSocketRoutes[F[_] : Async](controllers: Controller[F]*): WebSocketBuilder2[F] => HttpRoutes[F] = {
    val endpoints = controllers.flatMap(_.webSocketEndpoints).toList
    Http4sServerInterpreter().toWebSocketRoutes(endpoints)
  }
}
