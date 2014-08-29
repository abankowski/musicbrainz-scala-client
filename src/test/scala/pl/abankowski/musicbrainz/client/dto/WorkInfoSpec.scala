package pl.abankowski.musicbrainz.client.dto

import org.specs2.mutable.Specification

import pl.abankowski.musicbrainz.examples._
import play.api.libs.json.JsSuccess

class WorkInfoSpec extends Specification {
  "example json validate" should {
    "be JsSuccess" in {
      workJson.validate[WorkInfo] must beAnInstanceOf[JsSuccess[WorkInfo]]
    }
  }
}