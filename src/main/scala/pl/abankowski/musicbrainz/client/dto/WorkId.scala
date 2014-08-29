package pl.abankowski.musicbrainz.client.dto

import play.api.libs.json._

final case class WorkId(value: String) {
  require(value != null, "value cannot be null")
  require(value.nonEmpty, "value cannot be empty")
}

object WorkId {
  implicit object Format extends Format[WorkId] {
    override def reads(js: JsValue) = js match {
      case JsString(value) if value.nonEmpty => JsSuccess(WorkId(value))
      case _ => JsError(__, "validation.error.expected.WorkId")
    }

    override def writes(in: WorkId) = JsString(in.value)
  }

  def unapply(in: String) = Option(in).filter(_.nonEmpty).map(WorkId(_))
}