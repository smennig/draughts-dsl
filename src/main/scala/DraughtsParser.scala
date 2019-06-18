import model._

import scala.util.parsing.combinator._

class DraughtsParser extends RegexParsers {
  private def gameSituationParser: Parser[GameSituation] =
    playerParser ~
      "game setup:" ~
      fieldParser ^^ {
      case players ~ _ ~ fields => GameSituation(players, fields)
    }

  private def playerParser: Parser[(Player, Player)] =
    "Players" ~ word ~ "as" ~ color ~ "and" ~ word ~ "as" ~ color ~
      "active player:" ~ color ^^ {
      case _ ~ player1 ~ _ ~ player1Color ~ _ ~ player2 ~ _ ~ player2Color ~ _ ~ activePlayer =>
        if (activePlayer == player1Color) {
          (Player(name = player1, color = player1Color, turn = true), Player(name = player2, player2Color, turn = false))
        }
        else {
          (Player(name = player1, color = player1Color, turn = false), Player(name = player2, player2Color, turn = true))
        }
    }

  private def fieldParser: Parser[List[Field]] =
    rep(field)

  private def field: Parser[Field] =
    "" ~ letter ~ digit ~ piece ^^ {
      case _ ~ col ~ row ~ p =>
        val field = Field(column = mapLetterToCol(col), row = row)
        field.piece_(Some(p))
        field
    }

  private def piece: Parser[Piece] =
    word ~ color ^^ {
      case "king" ~ colour => King(colour)
      case "man" ~ colour => Man(colour)
    }

  private def mapLetterToCol(letter: String): Int = {
    "ABCDEFGH".indexOf(letter)
  }

  private def digit: Parser[Int] =
    """\d""".r ^^ (_.toInt)

  private def letter: Parser[String] =
    """\w""".r ^^ (_.toString)

  private def word: Parser[String] =
    """[\w]+""".r ^^ (_.toString)

  // every char until whitespace
  private def color: Parser[Colour.Value] =
    word ^^ {
      case "black" => Colour.BLACK
      case "white" => Colour.WHITE
    }

  def parseDSL(input: String): ParseResult[GameSituation] =
    parse(gameSituationParser, input)

}
