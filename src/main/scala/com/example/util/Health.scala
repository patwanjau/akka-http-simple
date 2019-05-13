package com.example.util

object Health {

  case class Version(version: String) extends AnyVal

  case class ConnectionStatus(status: Boolean) extends AnyVal

  case class Uptime(uptime: String) extends AnyVal

}

import Health._
case class Health(healthy: Boolean, version: Version, commit: String, uptime: Uptime, connection_status: ConnectionStatus)