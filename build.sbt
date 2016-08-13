name := """sander"""
organization  := "com.flurdy"

scalaVersion := "2.11.6"

version := "0.1.3"

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

libraryDependencies ++= {
   val akkaVersion = "2.4.8"
   Seq(
     "commons-daemon"    %  "commons-daemon" % "1.0.15",
     "com.typesafe.akka" %% "akka-actor" % akkaVersion,
     "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
     "org.scalatest"     %% "scalatest" % "2.2.4" % "test"
   )
}
