package pl.abankowski.musicbrainz.client.dto

import play.api.libs.json._

final case class ArtistId(value: String) {
  require(value != null, "value cannot be null")
  require(value.nonEmpty, "value cannot be empty")
}

object ArtistId {
  implicit object Format extends Format[ArtistId] {
    override def reads(js: JsValue) = js match {
      case JsString(value) if value.nonEmpty => JsSuccess(ArtistId(value))
      case _ => JsError(__, "validation.error.expected.ArtistId")
    }

    override def writes(in: ArtistId) = JsString(in.value)
  }

  def unapply(in: String) = Option(in).filter(_.nonEmpty).map(ArtistId(_))
}