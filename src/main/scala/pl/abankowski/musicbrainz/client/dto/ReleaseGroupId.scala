package pl.abankowski.musicbrainz.client.dto

import play.api.libs.json._

final case class ReleaseGroupId(value: String) {
  require(value != null, "value cannot be null")
  require(value.nonEmpty, "value cannot be empty")
}

object ReleaseGroupId {
  implicit object Format extends Format[ReleaseGroupId] {
    override def reads(js: JsValue) = js match {
      case JsString(value) if value.nonEmpty => JsSuccess(ReleaseGroupId(value))
      case _ => JsError(__, "validation.error.expected.ReleaseGroupId")
    }

    override def writes(in: ReleaseGroupId) = JsString(in.value)
  }

  def unapply(in: String) = Option(in).filter(_.nonEmpty).map(ReleaseGroupId(_))
}