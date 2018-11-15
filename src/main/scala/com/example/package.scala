package com

package object example {
import com.example.util.serverConfig
  val host = serverConfig.getString("host")
  val port = serverConfig.getInt("port")
}
