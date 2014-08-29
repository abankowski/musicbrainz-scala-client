package pl.abankowski.ws
import play.api.libs.iteratee._
import play.api.libs.iteratee.Input._
import play.api.libs.ws.{ WS => PlayWS }

class WSImpl extends WS {

  override def client = PlayWS.client(play.api.Play.current)

  override def url(url: String) = client.url(url)

  override def canonizeUrl(url: String) = url.endsWith("/") match {
    case true => url.slice(0, url.length - 1)
    case false => url
  }
}