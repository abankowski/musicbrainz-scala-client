package pl.abankowski.musicbrainz.client.dto
import play.api.libs.functional.syntax._

import play.api.libs.json._

final case class ReleaseGroupInfo(id: ReleaseGroupId, firstReleaseDate: String, title: String,
  artistCredit: ArtistCredit, primaryType: String,
  disambiguation: Option[String], secondaryTypes: List[String],
  releases: List[ReleaseGroupInfo.ReleaseReference]) {
}

object ReleaseGroupInfo {
  case class ReleaseReference(id: ReleaseId, title: String) {}

  object ReleaseReference {
    implicit val format = Json.format[ReleaseReference]
  }

  implicit val format: Format[ReleaseGroupInfo] = (
    (__ \ "id").format[ReleaseGroupId] ~
    (__ \ "first-release-date").format[String] ~
    (__ \ "title").format[String] ~
    (__ \ "artist-credit").format[ArtistCredit] ~
    (__ \ "primary-type").format[String] ~
    (__ \ "disambiguation").formatNullable[String] ~
    (__ \ "secondary-types").format[List[String]] ~
    (__ \ "releases").format[List[ReleaseReference]])(
      ReleaseGroupInfo.apply _, unlift(ReleaseGroupInfo.unapply))
}