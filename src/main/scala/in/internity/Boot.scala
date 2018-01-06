package in.internity

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import in.internity.http.RestService
import in.internity.stackoverflow.{Fetch, QuestionsActor}

import scala.concurrent.ExecutionContextExecutor
import scala.concurrent.duration._

/**
  * @author Shivansh <shiv4nsh@gmail.com>
  * @since 6/1/18
  */
object Boot extends App {

  import in.internity.config.AppConfig._

  implicit val actorSystem: ActorSystem = ActorSystem("ScalaAtStackOverflow")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = actorSystem.dispatcher

  RestService.run()
  val questionsActor = actorSystem.actorOf(QuestionsActor.props(questionsURL, authKey))
  actorSystem.scheduler.schedule(500 millis, 5000 milli) {
    questionsActor ! Fetch(tag, TimeCache.time)
  }
}

object TimeCache {
  var time: Double = 1515247849
}