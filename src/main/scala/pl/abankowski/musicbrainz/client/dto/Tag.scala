package pl.abankowski.musicbrainz.client.dto

import play.api.libs.json._

case class Tag(count: Int, name: String) {
  require(count >= 0, "count cannot be a negative number")
  require(name != null, "name cannot be null")
}

object Tag {
  implicit val format = Json.format[Tag]
}