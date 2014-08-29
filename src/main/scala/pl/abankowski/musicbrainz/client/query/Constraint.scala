package pl.abankowski.musicbrainz.client.query

sealed trait Constraint {
  def build: String
  def and(that: Constraint) = Builder(this).and(that)
  def or(that: Constraint) = Builder(this).or(that)
}

private[query] case class EntityFulltextConstraint(value: String) extends Constraint {
  def build = value
}

sealed trait FieldConstraint {
  def query(field: String, value: String) = field + ":\"" + value + '"'
}

private[query] case class Eq(field: String, value: String) extends Constraint with FieldConstraint {
  override def build = query(field, value)
}

private[query] case class Neq(field: String, value: String) extends Constraint with FieldConstraint {
  override def build = query("-" + field, value)
}

private[query] case class And(cts: List[Constraint]) extends Constraint {
  override def build = cts.foldLeft("")((query, constraint) => query + " AND " + constraint.toString())
}

private[query] case class Or(cts: List[Constraint]) extends Constraint {
  override def build = cts.foldLeft("")((query, constraint) => query + " OR " + constraint.toString())
}