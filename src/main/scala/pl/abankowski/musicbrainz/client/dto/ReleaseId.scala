package pl.abankowski.musicbrainz.client.dto

import play.api.libs.json._

final case class ReleaseId(value: String) {
  require(value != null, "value cannot be null")
  require(value.nonEmpty, "value cannot be empty")
}

object ReleaseId {
  implicit object Format extends Format[ReleaseId] {
    override def reads(js: JsValue) = js match {
      case JsString(value) if value.nonEmpty => JsSuccess(ReleaseId(value))
      case _ => JsError(__, "validation.error.expected.ReleaseId")
    }

    override def writes(in: ReleaseId) = JsString(in.value)
  }

  def unapply(in: String) = Option(in).filter(_.nonEmpty).map(ReleaseId(_))
}