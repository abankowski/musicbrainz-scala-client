package pl.abankowski.musicbrainz.client.dto
import play.api.libs.functional.syntax._

import play.api.libs.json._

final case class RecordingInfo(id: RecordingId, title: String, disambiguation: Option[String],
  artistCredit: List[ArtistCredit], isrcs: List[String], length: Int,
  releases: List[RecordingInfo.Release]) {
  require(id != null, "id cannot be null")
  require(title != null, "title cannot be null")
  require(disambiguation != null, "disambiguation cannot be null")
  require(artistCredit != null, "artistCredit cannot be null")
  require(isrcs != null, "isrcs cannot be null")
  require(length > 0, "length cannot be non positive number")
  require(releases != null, "releases cannot be null")
}

object RecordingInfo {

  case class Release(id: ReleaseId, title: String) {
    require(id != null, "id cannot be null")
    require(title != null, "title cannot be null")
  }

  object Release {
    implicit val format = Json.format[Release]
  }

  implicit val format: Format[RecordingInfo] = (
    (__ \ "id").format[RecordingId] ~
    (__ \ "title").format[String] ~
    (__ \ "disambiguation").formatNullable[String] ~
    (__ \ "artist-credit").format[List[ArtistCredit]] ~
    (__ \ "isrcs").format[List[String]] ~
    (__ \ "length").format[Int] ~
    (__ \ "releases").format[List[Release]])(
      RecordingInfo.apply _, unlift(RecordingInfo.unapply))
}