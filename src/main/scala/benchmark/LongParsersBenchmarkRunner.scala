package benchmark

import com.google.caliper.runner.CaliperMain

object LongParsersBenchmarkRunner extends App {
  CaliperMain.main(classOf[LongParsersBenchmark], args)
}
