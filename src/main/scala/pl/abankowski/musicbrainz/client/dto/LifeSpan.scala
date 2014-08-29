package pl.abankowski.musicbrainz.client.dto
import play.api.libs.functional.syntax._

import play.api.libs.json._

case class LifeSpan(ended: Option[Boolean], begin: Option[String], end: Option[String]) {
  require(begin != null, "begin cannot be null")
  require(end != null, "end cannot be null")
}

object LifeSpan {
  implicit val format: Format[LifeSpan] = (
    (__ \ 'ended).formatNullable[Boolean] ~
    (__ \ 'begin).formatNullable[String] ~
    (__ \ 'end).formatNullable[String])(
      LifeSpan.apply _, unlift(LifeSpan.unapply))
}