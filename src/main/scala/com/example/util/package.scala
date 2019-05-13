package com.example

import java.util.Properties

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.typesafe.config.{Config, ConfigFactory}

import scala.concurrent.ExecutionContext

package object util {
  val config: Config = ConfigFactory.load()

  val serverConfig: Config = config.getConfig("server")
  val mailConfig: Config = config.getConfig("mail")
  val appConfig: Config = config.getConfig("app")

  val emailRecipients: String = appConfig.getString("email.recipients")
  val senderEmailUsername: String = appConfig.getString("email.username")
  val senderEmailPassword: String = appConfig.getString("email.password")

  def mailProperties: Properties = {
    val props = new Properties
    props.put("mail.smtp.host", mailConfig.getString("smtp.host"))
    props.put("mail.smtp.port", mailConfig.getInt("smtp.port").toString)
    props.put("mail.smtp.auth", mailConfig.getBoolean("smtp.auth").toString)
    props.put("mail.smtp.starttls.enable", mailConfig.getBoolean("smtp.starttls.enable").toString)
    props
  }

  object Implicits {
    implicit val system: ActorSystem = ActorSystem()
    implicit val executionContext: ExecutionContext = system.dispatcher
    implicit val materializer: ActorMaterializer = ActorMaterializer()
  }

}
