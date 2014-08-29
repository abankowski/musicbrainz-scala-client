package pl.abankowski.musicbrainz.client.dto

import play.api.libs.json.Json

final case class WorkInfo(id: WorkId, title: String, language: String, iswcs: List[String]) {
  require(id != null, "id cannot be null")
  require(title != null, "title cannot be null")
  require(language != null, "language cannot be null")
  require(iswcs != null, "iswcs cannot be null")
}

object WorkInfo {
  implicit val format = Json.format[WorkInfo]
}