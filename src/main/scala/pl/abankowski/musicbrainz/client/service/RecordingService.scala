package pl.abankowski.musicbrainz.client.service
import scala.concurrent.Future

import pl.abankowski.musicbrainz.client.dto.RecordingId
import pl.abankowski.musicbrainz.client.dto.RecordingInfo
import pl.abankowski.musicbrainz.client.dto.ResourceResult
import pl.abankowski.musicbrainz.client.query._

trait RecordingService {
  def get(id: RecordingId): Future[Option[RecordingInfo]]
  def search(query: Query): Future[ResourceResult[RecordingInfo]]

}