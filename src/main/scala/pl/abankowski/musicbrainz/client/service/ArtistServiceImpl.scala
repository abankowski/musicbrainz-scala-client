package pl.abankowski.musicbrainz.client.service
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import com.google.inject.Inject

import pl.abankowski.musicbrainz.client.config.Config
import pl.abankowski.musicbrainz.client.dto.ArtistId
import pl.abankowski.musicbrainz.client.dto.ArtistInfo
import pl.abankowski.musicbrainz.client.dto.ResourceResult
import pl.abankowski.musicbrainz.client.query._
import pl.abankowski.ws.WS
import play.api.http.HeaderNames._
import play.api.http.Status._

private[service] class ArtistServiceImpl @Inject() (config: Config, ws: WS) extends MusicBrainzClient(config, ws)
  with ArtistService {

  override def get(id: ArtistId): Future[Option[ArtistInfo]] =
    url("/artist/" + id.value).withQueryString("inc" -> "aliases").get()
      .map(response =>
        response.status match {
          case OK => Some(response.json.as[ArtistInfo])
          case NOT_FOUND => None
        })

  override def search(query: Query) =
    url("/artist").withQueryString("query" -> query, "inc" -> "aliases").get()
      .map { response =>
        response.status match {
          case OK => ResourceResult.valueOf[ArtistInfo](response.json, "artist")
        }
      }
}