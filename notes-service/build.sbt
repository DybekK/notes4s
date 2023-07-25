ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.11"

libraryDependencies ++= Dependencies.All

lazy val root = (project in file("."))
  .settings(
    name := "notes-service"
  )
