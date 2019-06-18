

object Main extends App {
  val parser = new DraughtsParser
  val game = parser.parseDSL("Players Marco  as black and Marko  as white\nactive player: black\ngame setup:\n    A1 king black\n    B2 man white")
  print(game)
}
