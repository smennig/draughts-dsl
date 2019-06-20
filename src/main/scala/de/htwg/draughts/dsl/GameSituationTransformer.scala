package de.htwg.draughts.dsl

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

  def checkPlayers(gameSituation:  GameSituation):Either[String, GameSituation] =

    if(gameSituation.players._1.color == gameSituation.players._2.color){
      Left("Players can not have the same color")
    }else{
      Right(gameSituation)
    }

  def checkFields(gameSituation:  GameSituation):Either[String, GameSituation] =
    if (gameSituation.fields.map(f => (f.column,f.row)).distinct.size == gameSituation.fields.size){
      Right(gameSituation)
    }else{
      Left("Some fields are set more then once!")
    }



}
