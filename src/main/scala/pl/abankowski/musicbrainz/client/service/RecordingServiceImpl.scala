package pl.abankowski.musicbrainz.client.service
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import com.google.inject.Inject

import pl.abankowski.musicbrainz.client.config.Config
import pl.abankowski.musicbrainz.client.dto.RecordingId
import pl.abankowski.musicbrainz.client.dto.RecordingInfo
import pl.abankowski.musicbrainz.client.dto.ResourceResult
import pl.abankowski.musicbrainz.client.query._
import pl.abankowski.ws.WS
import play.api.http.HeaderNames._
import play.api.http.Status._

private[service] class RecordingServiceImpl @Inject() (config: Config, ws: WS)
  extends MusicBrainzClient(config, ws) with RecordingService {

  override def get(id: RecordingId): Future[Option[RecordingInfo]] =
    url("/recording/" + id.value).withQueryString("inc" -> "aliases").get()
      .map(response =>
        response.status match {
          case OK => Some(response.json.as[RecordingInfo])
          case NOT_FOUND => None
        })

  override def search(query: Query) =
    url("/recording").withQueryString("query" -> query, "inc" -> "aliases").get()
      .map { response =>
        response.status match {
          case OK => ResourceResult.valueOf[RecordingInfo](response.json, "recordings")
        }
      }
}