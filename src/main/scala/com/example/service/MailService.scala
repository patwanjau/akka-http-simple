package com.example.service

import javax.mail._
import javax.mail.internet.MimeMessage

object MailService extends App {

  val session = Session.getInstance(com.example.util.mailProperties,
    new Authenticator {
      override def getPasswordAuthentication: PasswordAuthentication = {
        new PasswordAuthentication("test.user@example.com", "testpass")
      }
    })

  val message = new MimeMessage(session)
  message.setFrom(Email.Sender("test.user@example.com").sender)
  message.setRecipients(Message.RecipientType.TO, "test.user@example.com")
  message.setSubject("Test Message")
  message.setText("Dear Sir/Madam,"+
  "\n\nPlease accept this humber email sent by machine troll")

  Transport.send(message)
}
