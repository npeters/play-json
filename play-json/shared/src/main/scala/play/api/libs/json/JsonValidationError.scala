/*
 * Copyright (C) 2009-2018 Lightbend Inc. <https://www.lightbend.com>
 */

package play.api.libs.json

/**
 * A JSON validation error representation.
 */
class JsonValidationError(lazyMessages: () => Seq[String], val args: Any*) {
  lazy val message = lazyMessages().last
  def messages: Seq[String] = lazyMessages()
}

object JsonValidationError {
  def apply(message: => String, args: Any*): JsonValidationError =
    new JsonValidationError(() => Seq(message), args: _*)

  def apply(messages: Seq[String], args: Any*): JsonValidationError =
    new JsonValidationError(() => messages, args: _*)
}
