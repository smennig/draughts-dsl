import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}

import de.htwg.draughts.dsl.{DraughtsParser, GameSituationTransformer}
import txt.{BoardCreator, DraughtsGui}
//import txt.D

import scala.io.Source

object Main extends App {
  val parser = new DraughtsParser
  val f = Source.fromFile("game-situation")
  val game =GameSituationTransformer.apply(parser.parseDSL(f.reader))
  f.close
  print(game)

  game match {
    case  Right(g)=> {
      writeToFile(BoardCreator.apply(g).toString, "BoardCreator.scala")
      writeToFile(DraughtsGui.apply(g).toString, "DraughtsGui.scala")
            }
  }


  def writeToFile(content: String, name: String) =
    Files.write(Paths.get("out",name), content.getBytes(StandardCharsets.UTF_8))
}
