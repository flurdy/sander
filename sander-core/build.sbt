name := """sander-core"""
organization  := "com.flurdy"

scalaVersion := "2.11.6"

version := "0.2.0"

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

libraryDependencies ++= {
   Seq(
     "commons-daemon"    %  "commons-daemon" % "1.0.15",
     "org.scalatest"     %% "scalatest" % "2.2.4" % "test"
   )
}
