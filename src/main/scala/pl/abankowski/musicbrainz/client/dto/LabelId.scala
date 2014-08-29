package pl.abankowski.musicbrainz.client.dto

import play.api.libs.json._

final case class LabelId(value: String) {
  require(value != null, "value cannot be null")
  require(value.nonEmpty, "value cannot be empty")
}

object LabelId {
  implicit object Format extends Format[LabelId] {
    override def reads(js: JsValue) = js match {
      case JsString(value) if value.nonEmpty => JsSuccess(LabelId(value))
      case _ => JsError(__, "validation.error.expected.LabelId")
    }

    override def writes(in: LabelId) = JsString(in.value)
  }

  def unapply(in: String) = Option(in).filter(_.nonEmpty).map(LabelId(_))
}