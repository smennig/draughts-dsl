package de.htwg.draughts.dsl

import de.htwg.draughts.model.Colour

object GameSituationTransformer {

  def apply(parseResult: Either[String, GameSituation]):
  Either[String, GameSituation] =
    for {
      parsed <- parseResult
      players <- checkPlayers(parsed)
      fields <- checkFields(players)
    } yield {
      fields
    }

  def checkPlayers(gameSituation: GameSituation): Either[String, GameSituation] =

    if (gameSituation.players._1.color == gameSituation.players._2.color) {
      Left("Players can not have the same color")
    } else {
      Right(gameSituation)
    }

  def checkFields(gameSituation: GameSituation): Either[String, GameSituation] =
    if (gameSituation.fields.exists(f => f.getColour == Colour.WHITE))
      Left(s"Only black fields are allowed to have pieces!\n" +
        s"Following Fileds are not allowed: ${gameSituation.fields.filter(f => f.getColour == Colour.WHITE)}")
    else if (gameSituation.fields.map(f => (f.column, f.row)).distinct.size == gameSituation.fields.size) {
      Right(gameSituation)
    } else {
      Left("Some fields are set more then once!")
    }


}
