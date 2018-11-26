package play.api.libs.json

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations._

@State(Scope.Benchmark)
class JsonParsing_02_ParseNotFoundField {
  @Param(Array("10", "100", "1000", "10000", "100000"))
  var n: Int = 100

  var stringToParse: String = _
  var json: JsValue = _
  @Setup
  def setup(): Unit = {
    val value = "42"
    stringToParse = HashCodeCollider.zeroHashCodeStrings.take(n)
      .mkString("""{"s":"s","""", s"""":$value,"""", s"""":$value,"i":1}""")
    json = Json.parse(stringToParse)
  }

  @Benchmark
  def parseGetFieldFound(): Option[Int] = {
    (json \ "i").asOpt[Int]
  }

  @Benchmark
  def parseGetFieldNotFound(): Option[Int] = {
    (json \ "notFound").asOpt[Int]
  }
}
