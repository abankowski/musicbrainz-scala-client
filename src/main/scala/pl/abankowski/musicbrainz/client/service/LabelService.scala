package pl.abankowski.musicbrainz.client.service
import scala.concurrent.Future
import pl.abankowski.musicbrainz.client.dto.LabelId
import pl.abankowski.musicbrainz.client.dto.LabelInfo
import pl.abankowski.musicbrainz.client.dto.ResourceResult
import pl.abankowski.musicbrainz.client.dto.ResourceResult
import pl.abankowski.musicbrainz.client.query._

trait LabelService {
  def get(id: LabelId): Future[Option[LabelInfo]]
  def search(query: Query): Future[ResourceResult[LabelInfo]]
}