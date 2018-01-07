package in.internity.http


import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.http.scaladsl.marshallers.xml.ScalaXmlSupport._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import in.internity.config.AppConfig

import scala.concurrent.Future
import scala.io.Source
import scala.xml.Elem

/**
  * @author Shivansh <shiv4nsh@gmail.com>
  * @since 7/1/18
  */


object RestService {

  val config = AppConfig
  val html = Source.fromFile(config.fileAddress).mkString


  def run()(implicit actorSystem: ActorSystem, actorMaterializer: ActorMaterializer): Future[ServerBinding] = {

    val routes =
      path("") {
        get {
          val resp: Elem = scala.xml.XML.loadString(html)
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
