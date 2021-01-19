package com.example.tasks

import akka.actor.ActorSystem

import java.util.Calendar
import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext
import scala.concurrent.duration.DurationInt

@Singleton
class CodeBlockTask @Inject()(actorSystem: ActorSystem)(implicit executionContext: ExecutionContext) {

  private val logger = org.slf4j.LoggerFactory.getLogger(classOf[CodeBlockTask])

  actorSystem.scheduler.schedule(initialDelay = 3.seconds, interval = 3.seconds) {
    // the block of code that will be executed
    logger.info(s"Executing something...time is - ${Calendar.getInstance().getTime}")
  }
}