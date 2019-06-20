package de.htwg.draughts.model

case class Player(name: String, color: Colour.Value, var turn: Boolean, var hasKing: Boolean = false, var pieces: Int = 12) {

    def turn_(): Unit = {
        turn = !turn
    }

    override def toString: String = "Name : " + name + " Farbe: " + color
}