package com.example

import java.util.Properties

import com.typesafe.config.{Config, ConfigFactory}

package object util {
  val config: Config = ConfigFactory.load()

  val serverConfig: Config = config.getConfig("server")
  val mailConfig: Config = config.getConfig("mail")

  def mailProperties: Properties = {
    val props = new Properties
    props.put("mail.smtp.host", mailConfig.getString("smtp.host"))
    props.put("mail.smtp.port", mailConfig.getInt("smtp.port"))
    props.put("mail.smtp.auth", mailConfig.getBoolean("smtp.auth"))
    props.put("mail.smtp.starttls.enable", mailConfig.getBoolean("smtp.starttls.enable"))
    return props
  }
}
