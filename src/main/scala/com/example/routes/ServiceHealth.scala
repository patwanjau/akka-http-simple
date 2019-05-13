package com.example.routes

import java.util.concurrent.TimeUnit

import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives.{complete, get, onComplete, path, pathPrefix, respondWithHeaders}
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import com.example.buildinfo.BuildInfo
import com.example.util.Health
import com.example.util.Implicits._
import akka.http.scaladsl.model.headers.CacheDirectives.`max-age`
import akka.http.scaladsl.model.headers.{Connection, `Cache-Control` => CacheControl}
import org.json4s.native.Serialization

class ServiceHealth {

  val route: Route =
    pathPrefix("public") {
      get {
        path("ping") {
          complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, "!!!!!"))
        } ~
          path("health") {
            onComplete(Http().singleRequest(HttpRequest(uri = Uri(s"/groups")))) {
              case scala.util.Success(response) =>
                response.discardEntityBytes(materializer)
                serviceHealthStatus(response.status == StatusCodes.OK || response.status == StatusCodes.NotFound)
              case scala.util.Failure(_) => serviceHealthStatus(false)
            }

          }
      } ~
        complete(HttpResponse(StatusCodes.Forbidden, entity = HttpEntity(ContentTypes.`text/plain(UTF-8)`, "Invalid request. Only `GET` requests allowed"), protocol = HttpProtocols.`HTTP/1.1`))
    }

  def uptime(timeInSeconds: Long): String = {
    val days = TimeUnit.SECONDS.toDays(timeInSeconds)
    val hours = TimeUnit.SECONDS.toHours(timeInSeconds - TimeUnit.DAYS.toSeconds(days))
    val minutes = TimeUnit.SECONDS.toMinutes(timeInSeconds - (TimeUnit.DAYS.toSeconds(days) + TimeUnit.HOURS.toSeconds(hours)))
    val seconds = timeInSeconds - (TimeUnit.DAYS.toSeconds(days) + TimeUnit.HOURS.toSeconds(hours) + TimeUnit.MINUTES.toSeconds(minutes))

    f"$days%02d:$hours%02d:$minutes%02d:$seconds%02d"
  }

  @inline
  private final def serviceHealthStatus(isConnected: Boolean) = {
    respondWithHeaders(CacheControl(`max-age`(3600)), Connection("close")) {
      import com.example.util.Health._
      val health = Health(healthy = isConnected, Version(BuildInfo.version), BuildInfo.gitCommit, Uptime(uptime(system.uptime)), ConnectionStatus(isConnected))
      val result = Serialization.write(health)(org.json4s.DefaultFormats)
      complete(HttpResponse(entity = HttpEntity(ContentTypes.`application/json`, result), protocol = HttpProtocols.`HTTP/1.1`))
    }
  }
}
