import sbt._
import Keys._

object Build extends Build {

  lazy val project = Project("root", file(".")).settings(

    // basics
    name := "scala-caliper",
    organization := "com.github.octo47",
    version := "0.1.0",
    scalaVersion := "2.10.1",

    // dependencies
    libraryDependencies ++= Seq(
      "com.google.code.java-allocation-instrumenter" % "java-allocation-instrumenter" % "2.1",
      "com.google.inject" % "guice" % "3.0",
      "com.google.inject.extensions" % "guice-multibindings" % "3.0",
      "com.google.guava" % "guava" % "14.0.1",
      "com.google.code.gson" % "gson" % "2.2.2",
      "joda-time" % "joda-time" % "2.1",
      "com.sun.jersey" % "jersey-client" % "1.11",
      "com.sun.jersey" % "jersey-core" % "1.11",
      "org.apache.commons" % "commons-math" % "2.2"
    ),

    // enable forking in run
    fork in run := true,

    // we need to add the runtime classpath as a "-cp" argument to the `javaOptions in run`, otherwise caliper
    // will not see the right classpath and die with a ConfigurationException
    javaOptions in run <++= (fullClasspath in Runtime) map { cp => Seq("-cp", sbt.Build.data(cp).mkString(":")) }
  )
}
