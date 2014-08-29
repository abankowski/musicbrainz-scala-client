package pl.abankowski.musicbrainz.client.dto
import play.api.libs.functional.syntax._

import play.api.libs.json._

case class ArtistCredit(name: String, joinphrase: String, artist: ArtistCredit.Artist) {
  require(name != null, "id cannot be null")
  require(joinphrase != null, "joinphrase cannot be null")
  require(artist != null, "artist cannot be null")
}

object ArtistCredit {
  case class Artist(id: ArtistId, name: String, sortName: String, disambiguation: Option[String]) {
    require(id != null, "id cannot be null")
    require(name != null, "name cannot be null")
    require(sortName != null, "sortName cannot be null")
    require(disambiguation != null, "disambiguation cannot be null")
  }

  object Artist {
    implicit val format: Format[Artist] = (
      (__ \ "id").format[ArtistId] ~
      (__ \ "name").format[String] ~
      (__ \ "sort-name").format[String] ~
      (__ \ "disambiguation").formatNullable[String])(
        Artist.apply _, unlift(Artist.unapply))
  }

  implicit val format = Json.format[ArtistCredit]
}