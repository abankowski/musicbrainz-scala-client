package pl.abankowski.musicbrainz.client.dto

import org.specs2.mutable.Specification

import pl.abankowski.musicbrainz.examples._
import play.api.libs.json.JsSuccess

class LabelInfoSpec extends Specification {
  "example json validate" should {
    "be JsSuccess" in {
      labelJson1.validate[LabelInfo] must beAnInstanceOf[JsSuccess[LabelInfo]]
    }

    "be JsSuccess" in {
      labelJson2.validate[LabelInfo] must beAnInstanceOf[JsSuccess[LabelInfo]]
    }
  }
}