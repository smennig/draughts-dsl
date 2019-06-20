
package de.htwg.draughts.model

class BoardCreator(size: Int = 8) {

    def setupFields(): DraughtsBoard = {
        val board = new DraughtsBoard(size, true)
        
        board.setPieceOnField(0)(0)(King (Colour.BLACK))
        board.setPieceOnField(1)(3)(Man (Colour.WHITE))
        board
    }

    def getPieceColour(row: Int): Colour.Value = {
        row match {
            case x if 0 to 2 contains x => Colour.BLACK
            case x if size - 3 until size contains x => Colour.WHITE
        }
    }

    def setupEmptyBoard(): DraughtsBoard = {
        new DraughtsBoard(size, true)
    }

}
