package pl.abankowski.musicbrainz.client.service
import scala.concurrent.Future

import pl.abankowski.musicbrainz.client.dto.ReleaseId
import pl.abankowski.musicbrainz.client.dto.ReleaseInfo
import pl.abankowski.musicbrainz.client.dto.ResourceResult
import pl.abankowski.musicbrainz.client.query._

trait ReleaseService {
  def get(id: ReleaseId): Future[Option[ReleaseInfo]]
  def search(query: Query): Future[ResourceResult[ReleaseInfo]]
}