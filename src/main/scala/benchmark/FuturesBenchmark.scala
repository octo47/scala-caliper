package benchmark

import com.google.caliper.runner.CaliperMain
import com.google.caliper.{Param, Benchmark}
import scala.concurrent.{Future, Promise}
import scala.util.Success
import scala.annotation.meta.beanSetter

/**
 * @author Andrey Stepachev
 */
class FuturesBenchmark {

  import scala.concurrent.ExecutionContext.Implicits.global

  @Param(Array[String]("10", "100", "1000"))
  @beanSetter
  var size = 0

  @Benchmark
  def accesViaFuture(reps: Int): Int = Util.run(reps) {
    val future = Future.sequence(
      for (i <- 0 until size) yield Future.successful(i)
    )
    val extractedI = future.value match {
      case Some(Success(x)) =>
        x
      case _ =>
        0
    }
  }
}

object FuturesBenchmark extends App {
  CaliperMain.main(classOf[FuturesBenchmark], args)
}
