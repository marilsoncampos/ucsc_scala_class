//
// Scala & Spark Versions ------------------------------------------------
//
scalaVersion := "2.11.7"
val sparkVersion = "2.1.0"

//
// Project Information ---------------------------------------------------
//

// Project name
name := "my-project" 

// Company Name
organization := "my-inc.com" 

// Project version
version := "1.01"

// Project participants
developers := List( 
  Developer("mcampos",
            "Marilson Campos",
            "marilson.campos@gmail.com",
            url("https://github.com/marilsoncampos")
  )
)

// Software licenses
licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html"))


//
// General configurationsProject -----------------------------------------
//

javacOptions ++= Seq(
  "-Xlint:deprecation",
  "-Xlint:unchecked",
  "-source", "1.8",
  "-target", "1.8",
  "-g:vars"
)

libraryDependencies ++= Seq(
  "org.scalatest"     %% "scalatest"   % "3.0.8" % Test withSources(),
  "junit"             %  "junit"       % "4.12"  % Test,
  "org.backuity.clist" %% "clist-core"   % "3.5.1",
  "org.backuity.clist" %% "clist-macros" % "3.5.1" % "provided",
  "org.apache.spark" %% "spark-core" % sparkVersion % "provided", 
  "org.apache.spark" %% "spark-sql" % sparkVersion % "provided"
)

resolvers ++= Seq(
)

scalacOptions ++= Seq(
  "-deprecation",                      // Emit warning and location for usages of deprecated APIs.
  "-encoding", "utf-8",                // Specify character encoding used by source files.
  "-explaintypes",                     // Explain type errors in more detail.
  "-feature",                          // Emit warning and location for usages of features that should be imported explicitly.
  "-language:existentials",            // Existential types (besides wildcard types) can be written and inferred
  "-language:experimental.macros",     // Allow macro definition (besides implementation and application)
  "-language:higherKinds",             // Allow higher-kinded types
  "-language:implicitConversions",     // Allow definition of implicit functions called views
  "-unchecked",                        // Enable additional warnings where generated code depends on assumptions.
  "-Xcheckinit",                       // Wrap field accessors to throw an exception on uninitialized access.
)

scalacOptions ++=
  scalaVersion {
    case sv if sv.startsWith("2.11") => List()
    case _ => Nil
  }.value

scalacOptions in (Compile, console) --= Seq("-Ywarn-unused:imports", "-Xfatal-warnings")



//
// Logging Configuration -------------------------------------------------
//

logBuffered in Test := false

// The level of log detail
logLevel := Level.Warn

// Show warnings and errors when compiling code.
logLevel in compile := Level.Warn

// During tests show detailed information
logLevel in test := Level.Info


//
// Other options ------- -------------------------------------------------
//

cancelable := true

initialCommands in console := """
                                |""".stripMargin

