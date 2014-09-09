package pl.abankowski.musicbrainz.client.service
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import com.google.inject.Inject

import pl.abankowski.musicbrainz.client.config.Config
import pl.abankowski.musicbrainz.client.dto.ReleaseId
import pl.abankowski.musicbrainz.client.dto.ReleaseInfo
import pl.abankowski.ws.WS
import play.api.http.HeaderNames._
import play.api.http.Status._
import pl.abankowski.musicbrainz.client.dto.ResourceResult
import pl.abankowski.musicbrainz.client.query._

private[service] class ReleaseServiceImpl @Inject() (config: Config, ws: WS)
  extends MusicBrainzClient(config, ws) with ReleaseService {

  override def get(id: ReleaseId): Future[Option[ReleaseInfo]] =
    url("/release/" + id.value).withQueryString("inc" -> "artist-credits labels discids recordings").get()
      .map(response =>
        response.status match {
          case OK => Some(response.json.as[ReleaseInfo])
          case NOT_FOUND => None
          case other if Range(500, 599).contains(other) =>
            throw MusicBrainzServerError(other)
        })

  override def search(query: Query) =
    url("/release").withQueryString("query" -> query, "inc" -> "artist-credits labels discids recordings").get()
      .map { response =>
        response.status match {
          case OK => ResourceResult.valueOf[ReleaseInfo](response.json, "releases")
          case other if Range(500, 599).contains(other) =>
            throw MusicBrainzServerError(other)
        }
      }
}