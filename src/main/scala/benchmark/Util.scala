package benchmark

import scala.util.Random

object Util {

  def run(reps:Int)(f: =>Unit) = for(i <- 0 until reps)(f)

  // sugar for building arrays using a per-cell init function
  def init[A:Manifest](size:Int)(init: =>A) = {
    val data = Array.ofDim[A](size)
    for (i <- 0 until size) data(i) = init
    data
  }

  // handy aliases into Random
  def nextInt() = Random.nextInt()
  def nextLong() = Random.nextLong()
  def nextFloat() = Random.nextFloat()
  def nextDouble() = Random.nextDouble()

  // sugar for building randomized arrays of various types
  def initInts(size:Int) = init(size)(nextInt())
  def initLongs(size:Int) = init(size)(nextLong())
  def initFloats(size:Int) = init(size)(nextFloat())
  def initDoubles(size:Int) = init(size)(nextDouble())
}
