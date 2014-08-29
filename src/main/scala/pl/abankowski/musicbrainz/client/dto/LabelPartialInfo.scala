package pl.abankowski.musicbrainz.client.dto
import play.api.libs.functional.syntax._
import play.api.libs.json._

final case class LabelPartialInfo(id: LabelId, name: String, sortName: String, labelCode: Option[Int],
  disambiguation: Option[String]) {

  require(id != null, "id cannot be null")
  require(name != null, "id cannot be null")
  require(sortName != null, "id cannot be null")
  require(disambiguation != null, "disambiguation cannot be null")
}

object LabelPartialInfo {
  implicit val format: Format[LabelPartialInfo] = (
    (__ \ "id").format[LabelId] ~
    (__ \ "name").format[String] ~
    (__ \ "sort-name").format[String] ~
    (__ \ "label-code").formatNullable[Int] ~
    (__ \ "disambiguation").formatNullable[String])(
      LabelPartialInfo.apply _, unlift(LabelPartialInfo.unapply))
}