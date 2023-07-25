import sbt.*

object Dependencies {
  private object Version {
    val ScalaTest = "3.2.15"
    val Http4s = "0.23.11"
    val Tapir = "1.6.2"
    val Circe = "0.14.5"
  }

  private val Scalatest: Seq[ModuleID] = Seq(
    "com.softwaremill.sttp.tapir" %% "tapir-sttp-stub-server" % Version.Tapir % Test,
    "com.softwaremill.sttp.client3" %% "circe" % "3.8.16" % Test,
    "org.scalatest" %% "scalatest" % Version.ScalaTest % Test
  )

  private val Http4s: Seq[ModuleID] = Seq(
    "org.http4s" %% "http4s-blaze-server" % Version.Http4s
  )

  private val Tapir: Seq[ModuleID] = Seq(
    "com.softwaremill.sttp.tapir" %% "tapir-http4s-server" % Version.Tapir,
    "com.softwaremill.sttp.tapir" %% "tapir-prometheus-metrics" % Version.Tapir,
    "com.softwaremill.sttp.tapir" %% "tapir-swagger-ui-bundle" % Version.Tapir,
    "com.softwaremill.sttp.tapir" %% "tapir-json-circe" % Version.Tapir,
  )

  private val Circe: Seq[ModuleID] = Seq(
    "org.http4s" %% "http4s-circe" % Version.Http4s,
    "io.circe" %% "circe-generic" % Version.Circe,
    "io.circe" %% "circe-literal" % Version.Circe,
  )

  val All: Seq[ModuleID] = Scalatest ++ Http4s ++ Tapir ++ Circe
}