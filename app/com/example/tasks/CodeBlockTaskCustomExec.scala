package com.example.tasks

import akka.actor.{ActorSystem, Props}
import com.example.actors.HelloActor
import com.example.actors.HelloActor.SayHello

import java.util.Calendar
import javax.inject.{Inject, Singleton}
import scala.concurrent.duration.DurationInt

@Singleton
class CodeBlockTaskCustomExec @Inject()(actorSystem: ActorSystem)(executor: TasksCustomExecutionContext) {

  private val logger = org.slf4j.LoggerFactory.getLogger(classOf[CodeBlockTaskCustomExec])

  actorSystem.scheduler.schedule(initialDelay = 3.seconds, interval = 3.seconds) ({
    // the block of code that will be executed
    logger.info(s"Executing custom task...time is - ${Calendar.getInstance().getTime}")
    for(a <- 1 to 100) {
      logger.info(s"Sending message to HelloActor...Amaresh - ${a}")
      val helloActor = actorSystem.actorOf(Props[HelloActor]().withDispatcher("tasks-executor"), s"hello-actor-${a}")
      helloActor ! SayHello(s"Amaresh - ${a}")
    }

  })(executor)
}