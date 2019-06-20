package de.htwg.draughts.model

case class Piece(colour: Colour.Value, kind: String) {
  def getColour: Colour.Value = colour

}
