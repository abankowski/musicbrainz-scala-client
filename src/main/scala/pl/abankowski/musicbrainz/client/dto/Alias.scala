package pl.abankowski.musicbrainz.client.dto
import play.api.libs.functional.syntax._

import play.api.libs.json._

case class Alias(name: String, sortName: String, locale: Option[String], primary: Option[Boolean]) {
  require(name != null, "name cannot be null")
  require(sortName != null, "name cannot be null")
  require(locale != null, "name cannot be null")
  require(primary != null, "name cannot be null")

  def isPrimary = primary.getOrElse(false)
}

object Alias {
  implicit val format: Format[Alias] = (
    (__ \ "name").format[String] ~
    (__ \ "sort-name").format[String] ~
    (__ \ "locale").formatNullable[String] ~
    (__ \ "primary").formatNullable[Boolean])(
      Alias.apply, unlift(Alias.unapply))
}