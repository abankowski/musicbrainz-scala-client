package pl.abankowski.musicbrainz.client.query

final case class Builder(constraint: Constraint) {

  def and(in: Constraint) = constraint match {
    case And(existing) => Builder(And(in :: existing))
    case existing => Builder(And(List(in, existing)))
  }

  def or(in: Constraint) = constraint match {
    case Or(existing) => Builder(Or(in :: existing))
    case existing => Builder(Or(List(in, existing)))
  }

  def build: Query = constraint.build
}

object Builder {
  def apply(in: String): Builder = apply(EntityFulltextConstraint(in))
}

