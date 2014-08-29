package pl.abankowski.musicbrainz.client.dto
import scala.collection._
import scala.collection.generic.GenericTraversableTemplate

import play.api.Logger
import play.api.libs.json._

final class ResourceResultUnpackException(msg: String) extends Exception(msg)

class ResourceResult[A](selection: List[A], offset: Int, totalCount: Int) extends LinearSeq[A] with Product
  with GenericTraversableTemplate[A, List] {

  override def productArity: Int = selection.productArity

  override def productElement(n: Int) = selection.productElement(n)

  override def apply(idx: Int) = selection(idx)

  override def length: Int = selection.length

  override def companion = selection.companion

  override def toList = selection

  def totalLength = totalCount

  def selectionOffset = offset

  def underlyingSelection = selection
}

object ResourceResult {
  private val logger = Logger(getClass)
  private def intVal(json: JsValue) = json match {
    case JsNumber(number) if number.isValidInt => Some(number.toInt)
    case _ => None
  }

  def valueOf[T](json: JsValue, field: String)(implicit reads: Reads[T]) = {
    val listReads = implicitly[Reads[List[T]]]
    def offset = intVal(json \ "offset")
    def count = intVal(json \ "count")
    def result = (json \ field).validate(listReads)

    logger.debug(s"Unpacking result from $json")

    (offset, count, result) match {
      case (Some(offset), Some(count), JsSuccess(result, _)) =>
        logger.info(s"ResourceResult for offset=$offset count=$count result=$result")
        new ResourceResult(result, offset, count)
      case _ =>
        logger.error("Cannot build Resource result due to the payload attributes issues")
        throw new ResourceResultUnpackException(s"Failed to unpack json to result offset=$offset count=$count result=$result")
    }
  }
}