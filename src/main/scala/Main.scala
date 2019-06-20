import scala.io.Source

object Main extends App {
  val parser = new DraughtsParser
  val f = Source.fromFile("game-situation")
  val game = parser.parseDSL(f.reader)
  f.close
  print(game)
}
