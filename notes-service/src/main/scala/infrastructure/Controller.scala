package infrastructure

import sttp.capabilities.WebSockets
import sttp.capabilities.fs2.Fs2Streams
import sttp.tapir.server.ServerEndpoint

trait Controller[F[_]] {
  val endpoints: List[ServerEndpoint[Any, F]] = List.empty
  val webSocketEndpoints: List[ServerEndpoint[Fs2Streams[F] with WebSockets, F]] = List.empty
}
