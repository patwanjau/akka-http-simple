package com.example

import akka.event.Logging
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.directives.DebuggingDirectives
import com.example.routes.SlackWebHooks
import com.example.util.Implicits._

object Main extends App {

  val routes = (new SlackWebHooks).route

  val loggedRoute = DebuggingDirectives.logRequestResult("Akka HTTP Simple", Logging.InfoLevel)(routes)

  Http().bindAndHandle(loggedRoute, host, port)
}
