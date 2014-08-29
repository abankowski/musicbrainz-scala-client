organization := "pl.abankowski"

name :="musicbrainz-scala-client"

scalaVersion :="2.10.1"

version :="0.0.1"

scalacOptions += "-feature"

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "org.mockito" % "mockito-all" % "1.9.5" % "test",
  "org.specs2" %% "specs2" % "1.13",
  "com.typesafe.play" %% "play-ws" % "2.3.3",
  "com.typesafe.play" %% "play-json" % "2.3.3",
  "com.google.guava" % "guava" % "17.0",
  "com.google.inject.extensions" % "guice-assistedinject" % "3.0",
  "com.tzavellas" % "sse-guice" % "0.7.1"
)
