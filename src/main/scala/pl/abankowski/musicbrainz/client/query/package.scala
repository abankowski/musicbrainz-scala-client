package pl.abankowski.musicbrainz.client
import scala.language.implicitConversions

package object query {
  type Query = String

  implicit def toFieldOps(in: String) = new FieldOps(in)
}