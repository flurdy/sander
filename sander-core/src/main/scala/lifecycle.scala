package com.flurdy.sander.server

import org.apache.commons.daemon._

/**
  * Application start and stop daemon.
  * Implement with a ApplicationDaemon and Application classes
  * and ServiceApplication object.
  * E.g.
  *
  * import akka.actor.{Props,ActorSystem}
  * import akka.io.IO
  * import com.flurdy.sander.server._
  * import spray.can.Http
  * import com.example.myapplication.MySuperServiceActor
  *
  * class ApplicationDaemon() extends AbstractApplicationDaemon {
  *   def application = new Application
  * }
  *
  * class Application() extends ReferenceApplication with Logging {
  *   val applicationName = "mysuperapplication"
  *   implicit val actorSystem = ActorSystem(s"$applicationName-system")  *
  *   def startApplication() {
  *     logger.info(s"Starting $applicationName Service")
  *     val myService = actorSystem.actorOf(Props[MySuperServiceActor], "my-super-service")
  *     IO(Http) ! Http.Bind(myService, interface = "0.0.0.0", port = 8080)
  *   }
  *
  *   def stopApplication() {
  *     logger.info(s"Stopping $applicationName Service")
  *     actorSystem.shutdown()
  *   }
  * }
  *
  * object ServiceApplication extends ServiceApplication{
  *   def createApplication()  = new ApplicationDaemon()
  *  }
  **/

trait ApplicationLifecycle {
    def start(): Unit
    def stop(): Unit
}

abstract class AbstractApplicationDaemon extends Daemon {
  def application: ApplicationLifecycle
  def init(daemonContext: DaemonContext) {}
  def start()   = application.start()
  def stop()    = application.stop()
  def destroy() = application.stop()
}

trait ServiceApplication extends App {

  def createApplication(): AbstractApplicationDaemon

  val application = createApplication()
  private[this] var cleanupAlreadyRun: Boolean = false

  def cleanup() {
    val previouslyRun = cleanupAlreadyRun
    cleanupAlreadyRun = true
    if (!previouslyRun) application.stop()
  }

  Runtime.getRuntime.addShutdownHook( new Thread( new Runnable {
        def run() {
          cleanup()
        }
  } ) )

  application.start()
}

abstract class ReferenceApplication extends ApplicationLifecycle {

  def startApplication(): Unit
  def stopApplication(): Unit

  private[this] var started: Boolean = false

  def start() = if (!started) {
                  started = true
                  startApplication()
                }

  def stop() = if (started) {
                 started = false
                 stopApplication()
               }
}
