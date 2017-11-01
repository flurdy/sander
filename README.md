## Sander

Utility classes to polish Scala applications.

#### Primitives

Implicit methods that seem common sense to me.

#### Lifecycle

Stop and start Scala applications

#### Akka

Actor and Probe factory



## Install

Modify your *build.sbt* and add this:


    resolvers += "flurdy-maven" at "http://dl.bintray.com/content/flurdy/maven"

    libraryDependencies += "com.flurdy" %% "sander-core" % "0.2.0"
    libraryDependencies += "com.flurdy" %% "sander-akka" % "0.2.0"
