package com.example.service

import javax.mail.Session

class MailService {

  val session = Session.getDefaultInstance(com.example.util.mailProperties)
  val transport = session.getTransport



}
