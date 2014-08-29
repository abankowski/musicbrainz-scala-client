package pl.abankowski.ws
import com.google.inject.Singleton

import com.tzavellas.sse.guice.ScalaModule

object WSModule extends ScalaModule {
  override def configure() {
    bind[WS].to[WSImpl].in[Singleton]
  }
}