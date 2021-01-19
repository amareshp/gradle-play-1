package com.example.actors

import akka.actor._

object HelloActor {
  def props = Props[HelloActor]

  case class SayHello(name: String)
}

class HelloActor extends Actor {
  import HelloActor._

  val logger = org.slf4j.LoggerFactory.getLogger(classOf[HelloActor])

  def receive = {
    case SayHello(name: String) =>
      logger.info(s"Received message...name = ${name}")
      sender() ! "Hello, " + name
      Thread.sleep(10000)
  }
}