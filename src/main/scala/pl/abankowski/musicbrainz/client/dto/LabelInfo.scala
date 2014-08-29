package pl.abankowski.musicbrainz.client.dto
import play.api.libs.functional.syntax._
import play.api.libs.json._

final case class LabelInfo(id: LabelId, name: String, sortName: String, labelCode: Option[Int], `type`: String,
  country: String, lifeSpan: LifeSpan, tags: Option[List[Tag]], disambiguation: Option[String], aliases: Option[List[Alias]]) {

  require(id != null, "id cannot be null")
  require(name != null, "id cannot be null")
  require(sortName != null, "id cannot be null")
  require(`type` != null, "id cannot be null")
  require(country != null, "id cannot be null")
  require(lifeSpan != null, "id cannot be null")
  require(tags != null, "id cannot be null")
  require(disambiguation != null, "disambiguation cannot be null")
}

object LabelInfo {
  implicit val format: Format[LabelInfo] = (
    (__ \ "id").format[LabelId] ~
    (__ \ "name").format[String] ~
    (__ \ "sort-name").format[String] ~
    (__ \ "label-code").formatNullable[Int] ~
    (__ \ "type").format[String] ~
    (__ \ "country").format[String] ~
    (__ \ "life-span").format[LifeSpan] ~
    (__ \ "tags").formatNullable[List[Tag]] ~
    (__ \ "disambiguation").formatNullable[String] ~
    (__ \ "aliases").formatNullable[List[Alias]])(
      LabelInfo.apply _, unlift(LabelInfo.unapply))
}