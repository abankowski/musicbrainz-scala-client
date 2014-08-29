package pl.abankowski.musicbrainz.client.service
import scala.concurrent.Future

import pl.abankowski.musicbrainz.client.dto.ArtistId
import pl.abankowski.musicbrainz.client.dto.ArtistInfo
import pl.abankowski.musicbrainz.client.dto.ResourceResult
import pl.abankowski.musicbrainz.client.query._

trait ArtistService {
  def get(id: ArtistId): Future[Option[ArtistInfo]]
  def search(query: Query): Future[ResourceResult[ArtistInfo]]
}