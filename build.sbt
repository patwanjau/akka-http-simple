name := "akka-http-simple"
organization := "The Example Company Inc"

version := "0.0.1-SNAPSHOT"
scalaVersion := "2.12.7"

enablePlugins(GitVersioning)
enablePlugins(BuildInfoPlugin)

lazy val akkaHttpVersion = "10.1.5"
lazy val akkaVersion = "2.5.18"
lazy val json4sVersion = "3.6.1"
val commitSha = SettingKey[String]("gitCommit")

commitSha := git.gitHeadCommit.value.getOrElse("Not Set").slice(0, 7)

buildInfoKeys := Seq[BuildInfoKey](name, version, commitSha)
buildInfoPackage := "com.example.buildinfo"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.sun.mail" % "javax.mail" % "1.6.2",
  "net.debasishg" %% "redisclient" % "3.8",
  "org.json4s" %% "json4s-native" % json4sVersion,
  "org.json4s" %% "json4s-jackson" % json4sVersion,

  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,
  "org.scalatest" %% "scalatest" % "3.0.5" % Test
)

dependencyOverrides ++= Seq(
  "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion
)

maintainer := "Patrick Wanjau"
assembly / assemblyJarName := "akka-http-simple.jar"
assembly / test  := {}
Test / logBuffered := false
Test / parallelExecution := false
