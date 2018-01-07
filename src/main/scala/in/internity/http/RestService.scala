package in.internity.http


import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.http.scaladsl.marshallers.xml.ScalaXmlSupport
import akka.http.scaladsl.model.MediaTypes
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import in.internity.config.AppConfig
import akka.http.scaladsl.marshallers.xml.ScalaXmlSupport._

import scala.concurrent.Future

/**
  * @author Shivansh <shiv4nsh@gmail.com>
  * @since 7/1/18
  */


object RestService {

  val config = AppConfig

  def run()(implicit actorSystem: ActorSystem, actorMaterializer: ActorMaterializer): Future[ServerBinding] = {

    val routes =
      path("") {
        get {
          val resp =
            <html>
              <body>
                <h1>StackOverflow Bot</h1>
                <h2>Visit me at: <a href="https://www.twitter.com/ScalaAtStack">ScalaAtStackOverflow</a></h2>
                <h3>Maintained By: <a href="https://www.twitter.com/Internity_learn">InternityFoundation</a></h3>
              </body>
            </html>
          complete(resp)
        }
      }

    Http().bindAndHandle(routes, config.address, config.port)
  }

  def stop(bindingFuture: Future[ServerBinding])(implicit actorSystem: ActorSystem): Future[Unit] = {
    implicit val executionContext = actorSystem.dispatcher

    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
  }
}
