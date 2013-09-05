package benchmark

import com.google.caliper.runner.CaliperMain
import com.google.caliper.{Param, Benchmark}
import scala.concurrent.Future
import scala.annotation.meta.beanSetter

/**
 * @author Andrey Stepachev
 */
class FuturesBenchmark {

  @Param(Array[String]("1", "10", "100"))
  @beanSetter
  var size: Int = 0

  @Benchmark
  def accesViaFuture(reps: Int): Int = Util.run(reps) {
    val futures =
      for (i <- 0 until size) yield Future.successful(someMethod(i))
    val ints = futures.map(f => f.value.get.get)
    ints.size
  }

  @Benchmark
  def accessViaEither(reps: Int): Int = Util.run(reps) {
    val eithes =
      for (i <- 0 until size) yield Left(someMethod(i))
    val ints = eithes.map(e => e.a)
    ints.size
  }

  @Benchmark
  def accessDirectly(reps: Int): Int = Util.run(reps) {
    val ints =
      for (i <- 0 until size) yield someMethod(i)
    ints.size
  }

  def someMethod(i: Int) = i * 2
}

object FuturesBenchmark extends App {
  CaliperMain.main(classOf[FuturesBenchmark], args)
}
