package pl.abankowski.musicbrainz.client.service
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import com.google.inject.Inject

import pl.abankowski.musicbrainz.client.config.Config
import pl.abankowski.musicbrainz.client.dto.LabelId
import pl.abankowski.musicbrainz.client.dto.LabelInfo
import pl.abankowski.musicbrainz.client.dto.ResourceResult
import pl.abankowski.musicbrainz.client.query._
import pl.abankowski.ws.WS
import play.api.http.HeaderNames._
import play.api.http.Status._

private[service] class LabelServiceImpl @Inject() (config: Config, ws: WS) extends MusicBrainzClient(config, ws)
  with LabelService {

  override def get(id: LabelId): Future[Option[LabelInfo]] =
    url("/label/" + id.value).withQueryString("inc" -> "aliases").get()
      .map(response =>
        response.status match {
          case OK => Some(response.json.as[LabelInfo])
          case NOT_FOUND => None
        })

  override def search(query: Query) =
    url("/label").withQueryString("query" -> query, "inc" -> "aliases").get()
      .map { response =>
        response.status match {
          case OK => ResourceResult.valueOf[LabelInfo](response.json, "labels")
        }
      }
}