package com.example.routes

import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.example.util.Implicits._

class SlackWebHooks {

  val route: Route = {
    get {
      complete(processRequest(Uri(kycProxyWebHook), entity = HttpEntity(ContentTypes.`application/json`, """{"text":"Hello, World!"}""")))
    }
  }

  def processRequest(uri: Uri, entity: RequestEntity) = {
    Http().singleRequest(HttpRequest(HttpMethods.POST, uri = uri, entity = entity))
  }
}
