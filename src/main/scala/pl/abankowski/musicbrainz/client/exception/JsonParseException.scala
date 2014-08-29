package pl.abankowski.musicbrainz.client.exception

import play.api.libs.json.JsError

class JsonParseException(message: String) extends Exception(message) {
}

object JsonParseException {
  def valueOf(error: JsError) = new JsonParseException(error.toString)
}