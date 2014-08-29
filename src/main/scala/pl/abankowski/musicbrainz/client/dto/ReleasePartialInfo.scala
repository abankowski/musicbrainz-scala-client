package pl.abankowski.musicbrainz.client.dto
import play.api.libs.functional.syntax._

import play.api.libs.json._

final case class ReleasePartialInfo(
  id: ReleaseId,
  title: String,
  date: String,
  textRepresentation: ReleasePartialInfo.TextRepresentation,
  releaseEvents: List[ReleaseEvent],
  country: String,
  status: String,
  quality: String,
  artistCredit: List[ArtistCredit],
  barcode: Option[String],
  disambiguation: Option[String],
  packaging: Option[String]) {

  require(id != null, "id cannot be null")
  require(title != null, "title cannot be null")
  require(date != null, "date cannot be null")
  require(textRepresentation != null, "textRepresentation cannot be null")
  require(releaseEvents != null, "releaseEvents cannot be null")
  require(country != null, "country cannot be null")
  require(status != null, "status cannot be null")
  require(quality != null, "quality cannot be null")
  require(artistCredit != null, "artistCredit cannot be null")
  require(barcode != null, "barcode cannot be null")
  require(disambiguation != null, "disambiguation cannot be null")
  require(packaging != null, "packaging cannot be null")
}

object ReleasePartialInfo {

  case class TextRepresentation(language: String, script: String) {
    require(language != null, "language cannot be null")
    require(script != null, "script cannot be null")
  }

  object TextRepresentation {
    implicit val format = Json.format[TextRepresentation]
  }

  implicit val format: Format[ReleasePartialInfo] = (
    (__ \ "id").format[ReleaseId] ~
    (__ \ "title").format[String] ~
    (__ \ "date").format[String] ~
    (__ \ "text-representation").format[TextRepresentation] ~
    (__ \ "release-events").format[List[ReleaseEvent]] ~
    (__ \ "country").format[String] ~
    (__ \ "status").format[String] ~
    (__ \ "quality").format[String] ~
    (__ \ "artist-credit").format[List[ArtistCredit]] ~
    (__ \ "barcode").formatNullable[String] ~
    (__ \ "disambiguation").formatNullable[String] ~
    (__ \ "packaging").formatNullable[String])(
      ReleasePartialInfo.apply _, unlift(ReleasePartialInfo.unapply))
}