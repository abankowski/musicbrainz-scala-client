package pl.abankowski.musicbrainz.client.config
import com.google.inject.Provides
import com.google.inject.Singleton
import com.tzavellas.sse.guice.ScalaModule

object ConfigPlayModule extends ScalaModule {
  override def configure() {}

  lazy val configuration = play.api.Play.current.configuration

  def url = configuration.getString("musicbrainz.url").getOrElse("http://musicbrainz.org/ws/2")

  @Provides @Singleton
  def config: Config = Config(url)
}