package com.flurdy.sander

import scala.concurrent.Future

package object primitives {

   implicit class OptionAsFuture[T](value: Option[T]) {
      def future: Future[T] = value.fold[Future[T]]
         { Future.failed(new NoSuchElementException) }
         { o => Future.successful(o) }
   }

   implicit class BooleanFold(boolean: Boolean) {
      def fold[B](l: B)(r: B => B): B = if(boolean) r(l) else l
   }

   implicit class BooleanSome(boolean: Boolean) {
      def some: Option[Boolean] = if(boolean) Some(true) else None
   }

   implicit class BooleanMap(boolean: Boolean) {
      def map[B](f: => B): Option[B] = if(boolean) Some(f) else None
   }
}
