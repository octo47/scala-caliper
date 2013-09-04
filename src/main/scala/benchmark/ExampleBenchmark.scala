package benchmark

import com.google.caliper.{BeforeExperiment, Param, Benchmark}
import scala.annotation.meta.beanSetter
import scala.util.Random
import com.google.caliper.runner.CaliperMain

/**
 * @author Andrey Stepachev
 */
class ExampleBenchmark {

  @beanSetter
  @Param(Array[String]("1", "10", "100"))
  var size: Int = 0
  var myObject: Array[Int] = Array()

  @BeforeExperiment
  def initList() = {
    myObject = new Array[Int](size)
  }

  @Benchmark
  def timeSomeAction(reps: Int): Int = {
    val obj = myObject
    for (i <- 1 to reps) {
      var sum = 0
      for(elem <- obj)
        sum += elem
    }
    0
  }
}

object ExampleBenchmark extends App {
  CaliperMain.main(classOf[ExampleBenchmark], args)
}
