package pl.abankowski.musicbrainz.client.dto

import play.api.libs.json._

final case class AreaId(value: String) {
  require(value != null, "value cannot be null")
  require(value.nonEmpty, "value cannot be empty")
}

object AreaId {
  implicit object Format extends Format[AreaId] {
    override def reads(js: JsValue) = js match {
      case JsString(value) if value.nonEmpty => JsSuccess(AreaId(value))
      case _ => JsError(__, "validation.error.expected.AreaId")
    }

    override def writes(in: AreaId) = JsString(in.value)
  }

  def unapply(in: String) = Option(in).filter(_.nonEmpty).map(AreaId(_))
}