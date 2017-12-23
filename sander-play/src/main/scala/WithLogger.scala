package models

import play.api.Logger

trait WithLogger {

  val logger: Logger = Logger(this.getClass())

}   

