package benchmark

import com.google.caliper.runner.CaliperMain
import com.google.caliper.Benchmark
import scala.concurrent.Promise
import scala.util.Success

/**
 * @author Andrey Stepachev
 */
class FuturesBenchmark {

  @Benchmark
  def accesViaFuture(reps: Int): Int = {
    for (i <- 1 to reps) {
      val promise = Promise[Int]()
      promise.success(i)
      val extractedI = promise.future.value match {
        case Some(Success(x)) =>
          x
        case _ =>
          0
      }
    }
    0
  }
}

object FuturesBenchmark extends App {
  CaliperMain.main(classOf[FuturesBenchmark], args)
}
