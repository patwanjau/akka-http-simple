package com

package object example {
import com.example.util.serverConfig
  val host = serverConfig.getString("host")
  val port = serverConfig.getInt("port")

  /** Redis Key-Value Server config {`hostname` and `port`} */
  private val redisPort: Int = serverConfig.getInt("redis-port")
  private val redisHost: String = serverConfig.getString("redis-host")

  val redisClient = new RedisClient(redisHost, redisPort)

  object Redis {

    def checkExistingCase(caseId: String): Boolean = {
      false
    }

    def getSystemCaseId(localCaseId: String): String = {
      redisClient.get(localCaseId).getOrElse("")
    }
}
