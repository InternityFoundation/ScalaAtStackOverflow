package in.internity.twitter

import in.internity.models.Question
import com.danielasfregola.twitter4s.TwitterRestClient
import com.danielasfregola.twitter4s.entities.Tweet

import scala.concurrent.Future


/**
  * @author Shivansh <shiv4nsh@gmail.com>
  * @since 6/1/18
  */
object TwitterCommunicator {

  val restClient = TwitterRestClient()
  def sendTweet(tweet: String): Future[Tweet] = {
    restClient.createTweet(tweet)
  }

  def formulateTweet(item: Question): String = {
    s"""Checkout this question on StackOverflow:${item.title} : ${item.link} #${item.tags.map(_.replace("-","_")).mkString(" #")} #internity"""
  }
}
