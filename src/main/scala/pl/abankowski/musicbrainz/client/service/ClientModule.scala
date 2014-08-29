package pl.abankowski.musicbrainz.client.service
import com.google.inject.Singleton

import com.tzavellas.sse.guice.ScalaModule

object ClientModule extends ScalaModule {
  override def configure() {
    bind[ArtistService].to[ArtistServiceImpl].in[Singleton]
    bind[LabelService].to[LabelServiceImpl].in[Singleton]
    bind[RecordingService].to[RecordingServiceImpl].in[Singleton]
    bind[ReleaseGroupService].to[ReleaseGroupServiceImpl].in[Singleton]
    bind[ReleaseService].to[ReleaseServiceImpl].in[Singleton]
    bind[WorkService].to[WorkServiceImpl].in[Singleton]
  }
}