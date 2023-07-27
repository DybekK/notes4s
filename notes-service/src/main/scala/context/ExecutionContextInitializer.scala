package context

import java.util.concurrent.Executors
import scala.concurrent.ExecutionContext

object ExecutionContextInitializer {
  def createHttpExecutionContext(): ExecutionContext =
    createExecutionContext()

  def createWebSocketExecutionContext(): ExecutionContext =
    createExecutionContext()

  private def createExecutionContext(nThreads: Int = 10): ExecutionContext = {
    val executor = Executors.newFixedThreadPool(nThreads)
    ExecutionContext.fromExecutor(executor)
  }
}
