package lab4

import lab4.State.GAME_NOT_FINISHED
import lab4.Move.*

class Controller(private val model: Model) {
    init {
        startGame()
    }

    private fun startGame() {
        model.printMap()
        while (model.state == GAME_NOT_FINISHED) {
            val input = readln().toCharArray()
            try {
                when(input[0].code){
                    65->{model.doMove(LEFT)}
                    97->{model.doMove(LEFT)}
                    68->{model.doMove(RIGHT)}
                    100->{model.doMove(RIGHT)}
                    87->{model.doMove(DOWN)}
                    119->{model.doMove(DOWN)}
                    83->{model.doMove(UP)}
                    115->{model.doMove(UP)}
                }
            } catch (e: Exception) {
                println(e.message)
            }
        }
        println("\nYou win!")
    }
}