package pl.abankowski.musicbrainz.client.service
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import com.google.inject.Inject

import pl.abankowski.musicbrainz.client.config.Config
import pl.abankowski.musicbrainz.client.dto.ReleaseGroupId
import pl.abankowski.musicbrainz.client.dto.ReleaseGroupInfo
import pl.abankowski.ws.WS
import play.api.http.HeaderNames._
import play.api.http.Status._
import pl.abankowski.musicbrainz.client.dto.ResourceResult
import pl.abankowski.musicbrainz.client.query._

private[service] class ReleaseGroupServiceImpl @Inject() (config: Config, ws: WS)
  extends MusicBrainzClient(config, ws) with ReleaseGroupService {

  override def get(id: ReleaseGroupId): Future[Option[ReleaseGroupInfo]] =
    url("/release-group/" + id.value).withQueryString("inc" -> "artist-credits releases").get()
      .map(response =>
        response.status match {
          case OK => Some(response.json.as[ReleaseGroupInfo])
          case NOT_FOUND => None
          case other if Range(500, 599).contains(other) =>
            throw MusicBrainzServerError(other)
        })

  override def search(query: Query) =
    url("/release-group").withQueryString("query" -> query, "inc" -> "artist-credits releases").get()
      .map { response =>
        response.status match {
          case OK => ResourceResult.valueOf[ReleaseGroupInfo](response.json, "release-groups")
          case other if Range(500, 599).contains(other) =>
            throw MusicBrainzServerError(other)
        }
      }
}