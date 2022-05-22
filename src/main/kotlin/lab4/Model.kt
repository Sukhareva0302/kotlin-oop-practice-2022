package lab4

import lab4.Cell.*
import lab4.State.*

enum class State {
    GAME_NOT_FINISHED,
    GAME_FINISHED
}

enum class Cell(private val textValue: Char) {
    PLAYER('P'),
    EXIT('E'),
    EMPTY('-'),
    WALL('#');

    override fun toString(): String = textValue.toString()
}

enum class Move(val x: Int, val y: Int) {
    UP(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    DOWN(0, -1);
}

interface ModelChangeListener {
    fun onModelChanged()
}

class Model(_height: Int, _length: Int, _map: Map<Pair<Int, Int>, Cell>) {
    private var playerPosition = _map.filterValues { it == PLAYER }.keys.first()
    private var height = _height
    private var length = _length
    private var map = _map.toMutableMap()
    var state: State = GAME_NOT_FINISHED
    private val listeners: MutableSet<ModelChangeListener> = mutableSetOf()

    fun addModelChangeListener(listener: ModelChangeListener) {
        listeners.add(listener)
    }

    fun removeModelChangeListener(listener: ModelChangeListener) {
        listeners.remove(listener)
    }

    fun doMove(move: Move) {
        val x = playerPosition.first + move.x
        val y = playerPosition.second + move.y
        if ((x < 0) || (y < 0)) {
            error("You can not left the field")
        } else {
            if (map[Pair(x, y)] == WALL) {
                error("You can not move through the wall")
            } else {
                if (checkWin(x, y)) {
                    map[Pair(playerPosition.first, playerPosition.second)] = EMPTY
                    map[Pair(x, y)] = PLAYER
                    playerPosition = Pair(x, y)
                    state = GAME_FINISHED
                    notifyListeners()
                    printMap()
                } else {
                    map[Pair(playerPosition.first, playerPosition.second)] = EMPTY
                    map[Pair(x, y)] = PLAYER
                    playerPosition = Pair(x, y)
                    notifyListeners()
                    printMap()
                }
            }
        }
    }

    private fun checkWin(x: Int, y: Int): Boolean {
        return (map[Pair(x, y)] == EXIT)
    }

    private fun notifyListeners() {
        listeners.forEach { it.onModelChanged() }
    }

    fun printMap() {
        for (y in 0..height) {
            for (x in 0 until length) {
                print(map[Pair(x, y)])
            }
            println()
        }
    }
}
