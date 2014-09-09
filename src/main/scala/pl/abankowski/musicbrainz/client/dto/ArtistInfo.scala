package pl.abankowski.musicbrainz.client.dto
import scala.util.control.Exception.catching

import play.api.libs.functional.syntax._
import play.api.libs.json._

final case class ArtistInfo(
  id: ArtistId,
  name: String,
  sortName: String,
  country: Option[String],
  disambiguation: Option[String],
  `type`: Option[String],
  lifeSpan: LifeSpan,
  aliases: Option[List[Alias]],
  score: Option[Int],
  tags: Option[List[Tag]],
  rating: Option[ArtistInfo.Rating],
  relations: Option[List[ArtistInfo.Relation]]) {

  require(id != null, "id cannot be null")
  require(name != null, "name cannot be null")
  require(sortName != null, "sortName cannot be null")
  require(country != null, "country cannot be null")
  require(disambiguation != null, "disambiguation cannot be null")
  require(`type` != null, "type cannot be null")
  require(lifeSpan != null, "lifeSpan cannot be null")
  require(aliases != null, "aliases cannot be null")
  require(tags != null, "tags cannot be null")
  require(rating != null, "rating cannot be null")
}

object ArtistInfo {

  implicit object TolerantInt extends Format[Int] {
    def reads(in: JsValue) = in match {
      case JsNumber(value) if value.isValidInt =>
        JsSuccess(value.toInt, __)
      case JsString(value) =>
        catching(classOf[NumberFormatException]).opt(value.toInt).map(JsSuccess(_, __))
          .getOrElse(JsError(__, "validate.error.expected.Score"))
    }

    override def writes(in: Int) = JsNumber(in)
  }

  case class Relation(
    typeId: String,
    end: Option[String],
    ended: Option[Boolean],
    url: Relation.Url,
    `type`: String,
    begin: Option[String],
    direction: String) {

    require(typeId != null, "typeId cannot be null")
    require(end != null, "end cannot be null")
    require(ended != null, "ended cannot be null")
    require(`type` != null, "type cannot be null")
    require(begin != null, "begin cannot be null")
    require(direction != null, "direction cannot be null")
  }

  object Relation {
    case class Url(id: String, resource: String) {
      require(id != null, "resource cannot be null")
      require(resource != null, "resource cannot be null")
    }

    object Url {
      implicit val format = Json.format[Url]
    }

    implicit val format: Format[Relation] = (
      (__ \ "type-id").format[String] ~
      (__ \ "end").formatNullable[String] ~
      (__ \ "ended").formatNullable[Boolean] ~
      (__ \ "url").format[Url] ~
      (__ \ "type").format[String] ~
      (__ \ "begin").formatNullable[String] ~
      (__ \ "direction").format[String])(
        Relation.apply _, unlift(Relation.unapply))
  }

  case class Rating(votesCount: Int, value: BigDecimal) {
    require(votesCount >= 0, "votesCount cannot be a negative number")
    require(value != null, "value cannot be null")
  }

  object Rating {
    implicit val format: Format[Rating] = (
      (__ \ "votes-count").format[Int] ~
      (__ \ "value").format[BigDecimal])(
        Rating.apply, unlift(Rating.unapply))
  }

  implicit val format: Format[ArtistInfo] = (
    (__ \ "id").format[ArtistId] ~
    (__ \ "name").format[String] ~
    (__ \ "sort-name").format[String] ~
    (__ \ "country").formatNullable[String] ~
    (__ \ "disambiguation").formatNullable[String] ~
    (__ \ "type").formatNullable[String] ~
    (__ \ "life-span").format[LifeSpan] ~
    (__ \ "aliases").formatNullable[List[Alias]] ~
    (__ \ "score").formatNullable(TolerantInt) ~
    (__ \ "tags").formatNullable[List[Tag]] ~
    (__ \ "rating").formatNullable[Rating] ~
    (__ \ "relations").formatNullable[List[Relation]])(
      ArtistInfo.apply, unlift(ArtistInfo.unapply))
}