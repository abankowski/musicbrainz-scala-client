package pl.abankowski.musicbrainz.client
import com.tzavellas.sse.guice.ScalaModule

import pl.abankowski.musicbrainz.client.config.ConfigPlayModule
import pl.abankowski.musicbrainz.client.service.ClientModule
import pl.abankowski.ws.WSModule

object ClientPlayModule extends ScalaModule {
  def configure() {
    install(WSModule)
    install(ConfigPlayModule)
    install(ClientModule)
  }
}