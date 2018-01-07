package in.internity.config

import com.typesafe.config.ConfigFactory

/**
  * @author Shivansh <shiv4nsh@gmail.com>
  * @since 6/1/18
  */
object AppConfig {
  val config = ConfigFactory.load()
  val questionsURL = config.getString("stackoverflow.questionsURL")
  val authKey = config.getString("stackoverflow.authKey")
  val tag = "scala"
  val address = config.getString("rest.url")
  val port = config.getInt("rest.port")
  val fileAddress=config.getString("fileAddress")
}
