package pl.abankowski.musicbrainz.client.dto
import play.api.libs.functional.syntax._

import play.api.libs.json._

final case class ReleaseEvent(area: ReleaseEvent.Area, date: String) {
  require(area != null, "area cannot be null")
  require(date != null, "date cannot be null")
}

object ReleaseEvent {
  case class Area(id: AreaId, disambiguation: Option[String], name: String, sortName: String) {
    require(id != null, "id cannot be null")
    require(disambiguation != null, "disambiguation cannot be null")
    require(name != null, "name cannot be null")
    require(sortName != null, "sortName cannot be null")
  }

  object Area {
    implicit val format: Format[Area] = (
      (__ \ 'id).format[AreaId] ~
      (__ \ 'disambiguation).formatNullable[String] ~
      (__ \ 'name).format[String] ~
      (__ \ 'name).format[String])(
        Area.apply _, unlift(Area.unapply))
  }

  implicit val format = Json.format[ReleaseEvent]
}