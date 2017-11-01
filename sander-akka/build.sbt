name := """sander-akka"""
organization  := "com.flurdy"

scalaVersion := "2.11.6"

version := "0.2.0"

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

libraryDependencies ++= {
   val akkaVersion = "2.4.8"
   Seq(
     "com.flurdy"        %% "sander-core" % "0.2.0",
     "commons-daemon"    %  "commons-daemon" % "1.0.15",
     "com.typesafe.akka" %% "akka-actor" % akkaVersion,
     "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
     "org.scalatest"     %% "scalatest" % "2.2.4" % "test"
   )
}
