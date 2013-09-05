package benchmark

import com.google.caliper.runner.CaliperMain
import scala.{specialized => spec}

import scala.math._
import scala.math.Ordering.Implicits._

import scala.{specialized => spec}
import com.google.caliper.api.Macrobenchmark


class AdditionBenchmark {

  val size = 1000 * 1000
  val longs = Util.initLongs(size)
  val x = Int.MaxValue.toLong

  // ====================================

  def generic1CountLessThan[T: Ordering : Manifest](t: T, data: Array[T]) = {
    var i = 0
    var count = 0
    while (i < data.length) {
      if (data(i) < t) count += 1
      i += 1
    }
    count
  }

  def generic2CountLessThan[T](t: T, data: Array[T])(implicit o: Ordering[T], m: Manifest[T]) = {
    var i = 0
    var count = 0
    while (i < data.length) {
      if (o.lt(data(i), t)) count += 1
      i += 1
    }
    count
  }

  def directCountLessThan(t: Long, data: Array[Long]) = {
    var i = 0
    var count = 0
    while (i < data.length) {
      if (data(i) < t) count += 1
      i += 1
    }
    count
  }

  @Macrobenchmark
  def timeGeneric1CountLessThan = generic1CountLessThan(x, longs)

  @Macrobenchmark
  def timeGeneric2CountLessThan = generic2CountLessThan(x, longs)

  @Macrobenchmark
  def timeDirectCountLessThan = directCountLessThan(x, longs)

  // ---------------------------------------

  def generic1CountEqualTo[T: Ordering : Manifest](t: T, data: Array[T]) = {
    var i = 0
    var count = 0
    while (i < data.length) {
      if (data(i) equiv t) count += 1
      i += 1
    }
    count
  }

  def generic2CountEqualTo[T](t: T, data: Array[T])(implicit o: Ordering[T], m: Manifest[T]) = {
    var i = 0
    var count = 0
    while (i < data.length) {
      if (o.equiv(data(i), t)) count += 1
      i += 1
    }
    count
  }

  def directCountEqualTo(t: Long, data: Array[Long]) = {
    var i = 0
    var count = 0
    while (i < data.length) {
      if (data(i) == t) count += 1
      i += 1
    }
    count
  }

  @Macrobenchmark
  def timeGeneric1CountEqualTo = generic1CountEqualTo(x, longs)

  @Macrobenchmark
  def timeGeneric2CountEqualTo = generic2CountEqualTo(x, longs)

  @Macrobenchmark
  def timeDirectCountEqualTo = directCountEqualTo(x, longs)

  // ---------------------------------------

  def generic1FindMax[T: Ordering : Manifest](data: Array[T]) = {
    var i = 0
    var m = data(0)
    while (i < data.length) {
      m = m.max(data(i))
      i += 1
    }
    m
  }

  def generic2FindMax[T](data: Array[T])(implicit o: Ordering[T], m: Manifest[T]) = {
    var i = 0
    var m = data(0)
    while (i < data.length) {
      m = o.max(m, data(i))
      i += 1
    }
    m
  }

  def directFindMax(data: Array[Long]) = {
    var i = 0
    var m = data(0)
    while (i < data.length) {
      m = scala.math.max(m, (data(i)))
      i += 1
    }
    m
  }

  @Macrobenchmark
  def timeGeneric1FindMax = generic1FindMax(longs)

  @Macrobenchmark
  def timeGeneric2FindMax = generic2FindMax(longs)

  @Macrobenchmark
  def timeDirectFindMax = directFindMax(longs)

}

object AdditionBenchmark extends App {
  CaliperMain.main(classOf[AdditionBenchmark], args)
}
