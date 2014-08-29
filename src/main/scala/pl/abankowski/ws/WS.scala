package pl.abankowski.ws
import play.api.libs.iteratee._
import play.api.libs.iteratee.Input._
import play.api.libs.ws.WSAPI
import play.api.libs.ws.WSRequestHolder

trait WS extends WSAPI {
  def canonizeUrl(url: String): String
  def url(url: String): WSRequestHolder
}