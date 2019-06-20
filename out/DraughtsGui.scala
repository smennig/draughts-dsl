
package de.htwg.draughts

import com.google.inject.Inject
import de.htwg.draughts.controller.GameControllerFactory
import de.htwg.draughts.model.{Colour, Player}
import de.htwg.draughts.view.gui.{BeginGameGUI, GameScene}
import javafx.embed.swing.JFXPanel
import scalafx.application.Platform
import scalafx.stage.Stage

class DraughtsGui @Inject()(gameControllerFactory: GameControllerFactory) extends Runnable {


    def run(): Unit = {
        // Shortcut to initialize JavaFX, force initialization by creating JFXPanel() object
        // (we will not use it for anything else)
        new JFXPanel()

        // Create a dialog stage and display it on JavaFX Application Thread
        Platform.runLater {

            //       Create dialog
            val gameStage: Stage = new Stage {
                title = "Draughts"

                resizable = false

            }

            val player1 = new Player("Daniel",Colour.BLACK, true)
            val player2 = new Player("Simon",Colour.WHITE, false)
                val controller = gameControllerFactory.create(player1, player2)
                gameStage.scene = new GameScene(controller, gameStage.hide)

            // Show dialog and wait till it is closed
            gameStage.showAndWait()
            // Force application exit
            System.exit(0)
        }
    }

}