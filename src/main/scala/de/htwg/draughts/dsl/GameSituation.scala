package de.htwg.draughts.dsl

import de.htwg.draughts.model.{Field, Player}

case class GameSituation (players: (Player,Player), fields : List[Field])
