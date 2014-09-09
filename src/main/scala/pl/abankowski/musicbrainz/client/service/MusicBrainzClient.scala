package pl.abankowski.musicbrainz.client.service
import pl.abankowski.musicbrainz.client.config.Config

import pl.abankowski.ws.WS
import play.api.Logger
import play.api.http.HeaderNames._

sealed case class MusicBrainzServerError(status: Int)
  extends Exception(s"HTTP request failed, status=$status")

abstract class MusicBrainzClient(config: Config, ws: WS) {
  val logger = Logger(getClass)

  val fmt = "fmt" -> "json"

  def url(path: String) =
    ws.url(ws.canonizeUrl(config.url) + path)
      .withHeaders(ACCEPT -> "application/json").withQueryString(fmt)
}