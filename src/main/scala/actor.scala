package com.flurdy.sander.actor

import akka.actor.{ActorContext,ActorRef,ActorSystem,Props}
import akka.testkit.TestProbe

trait WithActorFactory {
   implicit def actorFactory: ActorFactory
}

trait ActorFactory {

  def actorOf(props: Props)(implicit context: ActorContext) = context.actorOf( props)

  def actorOf(props: Props, name: String)(implicit context: ActorContext) = context.actorOf( props, name)
}

object ActorFactory extends ActorFactory

class ProbeFactory(implicit system: ActorSystem) extends ActorFactory {

  val first = TestProbe()
  val second = TestProbe()
  var unprobed = List(first,second)
  var probed: List[TestProbe] = Nil

  override def actorOf(props: Props)(implicit context: ActorContext) = {
      val probe = unprobed match {
           case head::tail =>
              unprobed = tail
              head
           case Nil => TestProbe()
         }
      probed = probe :: probed
      probe.ref
   }

  override def actorOf(props: Props, name: String)
                      (implicit context: ActorContext) = actorOf(props)

}
