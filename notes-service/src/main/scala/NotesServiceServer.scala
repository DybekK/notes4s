import cats.effect._
import infrastructure.HelloWorldController
import org.http4s.HttpRoutes
import org.http4s.blaze.server.BlazeServerBuilder
import org.http4s.server.Router
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

  override def run(args: List[String]): IO[ExitCode] = {
    for {
      _ <- IO(println("Starting server..."))

      // routes
      helloWorldRoutes = new HelloWorldController[IO]().all
      routes = toRoutes(helloWorldRoutes)

      // server
      _ <- BlazeServerBuilder[IO]
        .withExecutionContext(ec)
        .bindHttp(Port, Host)
        .withHttpApp(Router("/" -> routes).orNotFound)
        .resource
        .use(_ => IO.never)
    } yield ExitCode.Success
  }

  private def toRoutes(logicRoutes: List[ServerEndpoint[Any, IO]]): HttpRoutes[IO] = {
    val routes = logicRoutes ++ SwaggerInterpreter().fromServerEndpoints(logicRoutes, Title, Version)
    Http4sServerInterpreter[IO]().toRoutes(routes)
  }
}