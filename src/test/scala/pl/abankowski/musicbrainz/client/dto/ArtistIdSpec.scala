package pl.abankowski.musicbrainz.client.dto

import org.specs2.mutable.Specification
import play.api.libs.json._

class ArtistIdSpec extends Specification {
  "example json validate" should {
    "be JsSuccess" in {
      JsString("example-id").validate[ArtistId] must beAnInstanceOf[JsSuccess[ArtistId]]
    }

    "be JsError" in {
      JsString("").validate[ArtistId] must beAnInstanceOf[JsError]
    }
  }
}