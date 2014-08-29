package pl.abankowski.musicbrainz.client.dto
import play.api.libs.functional.syntax._

import play.api.libs.json._

final case class ReleaseInfo(
  id: ReleaseId,
  title: String,
  date: String,
  textRepresentation: ReleaseInfo.TextRepresentation,
  country: String,
  status: String,
  quality: String,
  releaseGroup: ReleaseInfo.ReleaseGroup,
  artistCredit: List[ArtistCredit],
  labelInfo: List[ReleaseInfo.LabelReference],
  media: List[ReleaseInfo.Media],
  barcode: Option[String],
  disambiguation: Option[String],
  packaging: Option[String],
  asin: Option[String]) {
}

object ReleaseInfo {

  case class TextRepresentation(language: String, script: String) {
    require(language != null, "language cannot be null")
    require(script != null, "script cannot be null")
  }

  object TextRepresentation {
    implicit val format = Json.format[TextRepresentation]
  }

  case class ReleaseGroup(id: ReleaseGroupId, primaryType: String) {
    require(id != null, "id cannot be null")
    require(primaryType != null, "primaryType cannot be null")
  }

  object ReleaseGroup {
    implicit val format: Format[ReleaseGroup] = (
      (__ \ "id").format[ReleaseGroupId] ~
      (__ \ "primary-type").format[String])(
        ReleaseGroup.apply _, unlift(ReleaseGroup.unapply))
  }

  case class LabelReference(label: LabelPartialInfo, catalogNumber: String) {
    require(label != null, "label cannot be null")
    require(catalogNumber != null, "catalogNumber cannot be null")
  }

  object LabelReference {
    implicit val format: Format[LabelReference] = (
      (__ \ "label").format[LabelPartialInfo] ~
      (__ \ "catalog-number").format[String])(
        LabelReference.apply _, unlift(LabelReference.unapply))
  }

  case class Media(trackOffset: Int, trackCount: Int, title: String, format: String, discids: List[Media.Disc],
    tracks: List[Media.Track]) {
    require(trackOffset >= 0, "trackOffset cannot be negative number")
    require(trackCount > 0, "trackCount cannot be non positive number")
    require(title != null, "title cannot be null")
    require(format != null, "format cannot be null")
    require(discids != null, "discids cannot be null")
    require(tracks != null, "tracks cannot be nullr")
  }

  object Media {
    case class Disc(id: String, sectors: Int) {
      require(id != null, "id cannot be null")
      require(sectors > 0, "sectors cannot be non positive number")
    }

    object Disc {
      implicit val format = Json.format[Disc]
    }

    case class Track(number: String, length: Int, title: String, recording: Track.Recording, artistCredit: List[ArtistCredit]) {
      require(number != null, "tracks cannot be null")
      require(length > 0, "length cannot be non positive number")
      require(title != null, "title cannot be null")
      require(recording != null, "recording cannot be null")
      require(artistCredit != null, "artistCredit cannot be null")
    }

    object Track {
      case class Recording(id: RecordingId, title: String, disambiguation: Option[String], length: String, video: Int,
        artistCredit: List[ArtistCredit]) {
        require(id != null, "id cannot be null")
        require(title != null, "title cannot be null")
        require(disambiguation != null, "disambiguation cannot be null")
        require(length != null, "length cannot be null")
        require(video >= 0, "video cannot be negative number")
        require(artistCredit != null, "artistCredit cannot be null")
      }

      object Recording {
        implicit val format: Format[Recording] = (
          (__ \ "id").format[RecordingId] ~
          (__ \ "title").format[String] ~
          (__ \ "disambiguation").formatNullable[String] ~
          (__ \ "length").format[String] ~
          (__ \ "video").format[Int] ~
          (__ \ "artist-credit").format[List[ArtistCredit]])(
            Recording.apply _, unlift(Recording.unapply))
      }

      implicit val format: Format[Track] = (
        (__ \ "number").format[String] ~
        (__ \ "length").format[Int] ~
        (__ \ "title").format[String] ~
        (__ \ "recording").format[Recording] ~
        (__ \ "artist-credit").format[List[ArtistCredit]])(
          Track.apply _, unlift(Track.unapply))
    }

    implicit val format: Format[Media] = (
      (__ \ "track-offset").format[Int] ~
      (__ \ "track-count").format[Int] ~
      (__ \ "title").format[String] ~
      (__ \ "format").format[String] ~
      (__ \ "discids").format[List[Disc]] ~
      (__ \ "tracks").format[List[Track]])(
        Media.apply _, unlift(Media.unapply))
  }

  implicit val format: Format[ReleaseInfo] = (
    (__ \ "id").format[ReleaseId] ~
    (__ \ "title").format[String] ~
    (__ \ "date").format[String] ~
    (__ \ "text-representation").format[TextRepresentation] ~
    (__ \ "country").format[String] ~
    (__ \ "status").format[String] ~
    (__ \ "quality").format[String] ~
    (__ \ "release-group").format[ReleaseGroup] ~
    (__ \ "artist-credit").format[List[ArtistCredit]] ~
    (__ \ "label-info").format[List[LabelReference]] ~
    (__ \ "media").format[List[Media]] ~
    (__ \ "barcode").formatNullable[String] ~
    (__ \ "disambiguation").formatNullable[String] ~
    (__ \ "packaging").formatNullable[String] ~
    (__ \ "asin").formatNullable[String])(
      ReleaseInfo.apply _, unlift(ReleaseInfo.unapply))
}