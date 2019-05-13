package com.example

import com.example.util.appConfig

package object routes {
  val kycProxyWebHook = appConfig.getString("slack.webhooks.kycproxy")
}
