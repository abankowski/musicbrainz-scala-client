package pl.abankowski.musicbrainz.client.query

final class FieldOps(name: String) {
  def equalsValue(value: String) = Eq(name, value)
  def notEqualsValue(value: String) = Neq(name, value)
  def startsWith(start: String) = ???
  def startsWith(start: String, end: String) = ???
}