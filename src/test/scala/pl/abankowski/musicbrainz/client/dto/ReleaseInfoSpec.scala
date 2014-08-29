package pl.abankowski.musicbrainz.client.dto

import org.specs2.mutable.Specification

import pl.abankowski.musicbrainz.examples._
import play.api.libs.json.JsSuccess

class ReleaseInfoSpec extends Specification {
  "example json validate" should {
    "be JsSuccess" in {
      releaseJson.validate[ReleaseInfo] must beAnInstanceOf[JsSuccess[ReleaseInfo]]
    }
  }
}