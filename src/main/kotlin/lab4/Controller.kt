package lab4

import lab4.State.GAME_NOT_FINISHED

class Controller(private val model: Model) {
    init {
        startGame()
    }

    private fun startGame() {
        model.printMap()
        while (model.state == GAME_NOT_FINISHED) {
            val input = readln().toCharArray()
            try {
                model.doMove(input[0].code)
            } catch (e: Exception) {
                println(e.message)
            }
        }
        println("\nYou win!")
    }
}