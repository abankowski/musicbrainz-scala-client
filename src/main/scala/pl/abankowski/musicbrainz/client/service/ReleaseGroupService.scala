package pl.abankowski.musicbrainz.client.service
import scala.concurrent.Future

import pl.abankowski.musicbrainz.client.dto.ReleaseGroupId
import pl.abankowski.musicbrainz.client.dto.ReleaseGroupInfo
import pl.abankowski.musicbrainz.client.dto.ResourceResult
import pl.abankowski.musicbrainz.client.query._

trait ReleaseGroupService {
  def get(id: ReleaseGroupId): Future[Option[ReleaseGroupInfo]]
  def search(query: Query): Future[ResourceResult[ReleaseGroupInfo]]
}