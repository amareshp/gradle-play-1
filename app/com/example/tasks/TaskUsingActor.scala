package com.example.tasks

import akka.actor.{ActorSystem, Props}
import com.example.actors.HelloActor
import com.example.actors.HelloActor.SayHello

import javax.inject.{Inject, Singleton}

@Singleton
class TaskUsingActor @Inject()(actorSystem: ActorSystem)(implicit ec: TasksCustomExecutionContext) {

  private val logger = org.slf4j.LoggerFactory.getLogger(classOf[TaskUsingActor])
  //val helloActor = actorSystem.actorOf(HelloActor.props, "hello-actor")

  for(a <- 1 to 100) {
    val helloActor = actorSystem.actorOf(Props[HelloActor]().withDispatcher("tasks-executor"), s"hello-actor-${a}")
    logger.info(s"Sending message to HelloActor...Amaresh - ${a}")
    helloActor ! SayHello(s"Amaresh - ${a}")
  }

}