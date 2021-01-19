package com.example.actors

import com.google.inject.AbstractModule
import play.api.libs.concurrent.AkkaGuiceSupport

class MyModule extends AbstractModule with AkkaGuiceSupport {
  def configure = {
    bindActor[HelloActor]("hello-actor")
  }
}
