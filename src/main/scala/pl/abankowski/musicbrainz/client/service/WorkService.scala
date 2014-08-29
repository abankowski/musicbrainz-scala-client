package pl.abankowski.musicbrainz.client.service
import scala.concurrent.Future

import pl.abankowski.musicbrainz.client.dto.WorkId
import pl.abankowski.musicbrainz.client.dto.WorkInfo
import pl.abankowski.musicbrainz.client.dto.ResourceResult
import pl.abankowski.musicbrainz.client.query._

trait WorkService {
  def get(id: WorkId): Future[Option[WorkInfo]]
  def search(query: Query): Future[ResourceResult[WorkInfo]]
}