package pl.abankowski.musicbrainz.client.dto

import org.specs2.mutable.Specification

import pl.abankowski.musicbrainz.examples._
import play.api.libs.json._

class ArtistInfoSpec extends Specification {
  "example json validate" should {
    "be JsSuccess" in {
      artistJson1.validate[ArtistInfo] must beAnInstanceOf[JsSuccess[ArtistInfo]]
    }

    "be JsSuccess" in {
      artistJson2.validate[ArtistInfo] must beAnInstanceOf[JsSuccess[ArtistInfo]]
    }

    "be JsSuccess" in {
      artistSearchJson1.validate[List[ArtistInfo]] must beAnInstanceOf[JsSuccess[List[ArtistInfo]]]
    }
  }
}