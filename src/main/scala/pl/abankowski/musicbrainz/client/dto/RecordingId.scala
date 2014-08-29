package pl.abankowski.musicbrainz.client.dto

import play.api.libs.json._

final case class RecordingId(value: String) {
  require(value != null, "value cannot be null")
  require(value.nonEmpty, "value cannot be empty")
}

object RecordingId {
  implicit object Format extends Format[RecordingId] {
    override def reads(js: JsValue) = js match {
      case JsString(value) if value.nonEmpty => JsSuccess(RecordingId(value))
      case _ => JsError(__, "validation.error.expected.RecordingId")
    }

    override def writes(in: RecordingId) = JsString(in.value)
  }

  def unapply(in: String) = Option(in).filter(_.nonEmpty).map(RecordingId(_))
}