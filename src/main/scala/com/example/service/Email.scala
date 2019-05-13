package com.example.service

object Email {

  case class Sender(sender: String) extends AnyVal

  case class Recipient(recipient: String) extends AnyVal

  case class Subject(subject: String) extends AnyVal

  case class Message(message: String) extends AnyVal

}
