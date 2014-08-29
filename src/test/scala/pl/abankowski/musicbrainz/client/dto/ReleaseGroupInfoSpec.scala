package pl.abankowski.musicbrainz.client.dto

import org.specs2.mutable.Specification

import pl.abankowski.musicbrainz.examples._
import play.api.libs.json.JsSuccess

class ReleaseGroupInfoSpec extends Specification {
  "example json validate" should {
    "be JsSuccess" in {
      releaseGroupJson.validate[ReleaseGroupInfo] must beAnInstanceOf[JsSuccess[ReleaseGroupInfo]]
    }
  }
}