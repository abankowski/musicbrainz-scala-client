package pl.abankowski.musicbrainz.client.service
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import com.google.inject.Inject

import pl.abankowski.musicbrainz.client.config.Config
import pl.abankowski.musicbrainz.client.dto.ResourceResult
import pl.abankowski.musicbrainz.client.dto.WorkId
import pl.abankowski.musicbrainz.client.dto.WorkInfo
import pl.abankowski.musicbrainz.client.query._
import pl.abankowski.ws.WS
import play.api.http.HeaderNames._
import play.api.http.Status._

private[service] class WorkServiceImpl @Inject() (config: Config, ws: WS) extends MusicBrainzClient(config, ws)
  with WorkService {

  override def get(id: WorkId): Future[Option[WorkInfo]] =
    url("/work/" + id.value).get()
      .map(response =>
        response.status match {
          case OK => Some(response.json.as[WorkInfo])
          case NOT_FOUND => None
          case other if Range(500, 599).contains(other) =>
            throw MusicBrainzServerError(other)
        })

  override def search(query: Query) =
    url("/work").withQueryString("query" -> query).get()
      .map(response => response.status match {
        case OK =>
          ResourceResult.valueOf[WorkInfo](response.json, "works")
        case other if Range(500, 599).contains(other) =>
          throw MusicBrainzServerError(other)
      })
}