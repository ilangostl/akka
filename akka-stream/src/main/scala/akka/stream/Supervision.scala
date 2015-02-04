/**
 * Copyright (C) 2015 Typesafe Inc. <http://www.typesafe.com>
 */
package akka.stream

import scala.util.control.NonFatal

object Supervision {
  sealed trait Directive

  // FIXME should Stop be named Fail?
  // FIXME should it be possible to convert the thrown exception to another exception?
  case object Stop extends Directive
  case object Resume extends Directive
  case object Restart extends Directive

  type Decider = PartialFunction[Throwable, Directive]

  val stoppingDecider: Decider = {
    case NonFatal(_) ⇒ Stop
  }

  val resumingDecider: Decider = {
    case NonFatal(_) ⇒ Resume
  }

  val restartingDecider: Decider = {
    case NonFatal(_) ⇒ Restart
  }

}
