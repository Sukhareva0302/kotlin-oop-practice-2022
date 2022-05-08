package lab4

import lab4.Cell.*
import lab4.State.*
import java.io.File

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

interface ModelChangeListener {
    fun onModelChanged()
}

class Model {
    private lateinit var playerPosition: Pair<Int, Int>
    private var height = 0
    private var length = 0
    private var map = readMap().toMutableMap()
    var state: State = GAME_NOT_FINISHED
    private val listeners: MutableSet<ModelChangeListener> = mutableSetOf()

    fun addModelChangeListener(listener: ModelChangeListener) {
        listeners.add(listener)
    }

    fun removeModelChangeListener(listener: ModelChangeListener) {
        listeners.remove(listener)
    }

    fun doMove(move: Int) {
        val x = playerPosition.first
        val y = playerPosition.second
        when (move) {
            65 -> {
                if (x - 1 < 0) {
                    error("You can not left the field")
                } else {
                    if (map[Pair(x - 1, y)] == WALL) {
                        error("You can not move through the wall")
                    } else {
                        if (checkWin(x - 1, y)) {
                            map[Pair(x, y)] = EMPTY
                            map[Pair(x - 1, y)] = PLAYER
                            playerPosition = Pair(x - 1, y)
                            state = GAME_FINISHED
                            notifyListeners()
                            printMap()
                        } else {
                            map[Pair(x, y)] = EMPTY
                            map[Pair(x - 1, y)] = PLAYER
                            playerPosition = Pair(x - 1, y)
                            notifyListeners()
                            printMap()
                        }
                    }
                }
            }
            97 -> {
                if (x - 1 < 0) {
                    error("You can not left the field")
                } else {
                    if (map[Pair(x - 1, y)] == WALL) {
                        error("You can not move through the wall")
                    } else {
                        if (checkWin(x - 1, y)) {
                            map[Pair(x, y)] = EMPTY
                            map[Pair(x - 1, y)] = PLAYER
                            playerPosition = Pair(x - 1, y)
                            state = GAME_FINISHED
                            notifyListeners()
                            printMap()
                        } else {
                            map[Pair(x, y)] = EMPTY
                            map[Pair(x - 1, y)] = PLAYER
                            playerPosition = Pair(x - 1, y)
                            notifyListeners()
                            printMap()
                        }
                    }
                }
            }
            68 -> {
                if (x + 1 >= length) {
                    error("You can not left the field")
                } else {
                    if (map[Pair(x + 1, y)] == WALL) {
                        error("You can not move through the wall")
                    } else {
                        if (checkWin(x + 1, y)) {
                            map[Pair(x, y)] = EMPTY
                            map[Pair(x + 1, y)] = PLAYER
                            playerPosition = Pair(x + 1, y)
                            state = GAME_FINISHED
                            notifyListeners()
                            printMap()
                        } else {
                            map[Pair(x, y)] = EMPTY
                            map[Pair(x + 1, y)] = PLAYER
                            playerPosition = Pair(x + 1, y)
                            notifyListeners()
                            printMap()
                        }
                    }
                }
            }
            100 -> {
                if (x + 1 >= length) {
                    error("You can not left the field")
                } else {
                    if (map[Pair(x + 1, y)] == WALL) {
                        error("You can not move through the wall")
                    } else {
                        if (checkWin(x + 1, y)) {
                            map[Pair(x, y)] = EMPTY
                            map[Pair(x + 1, y)] = PLAYER
                            playerPosition = Pair(x + 1, y)
                            state = GAME_FINISHED
                            notifyListeners()
                            printMap()
                        } else {
                            map[Pair(x, y)] = EMPTY
                            map[Pair(x + 1, y)] = PLAYER
                            playerPosition = Pair(x + 1, y)
                            notifyListeners()
                            printMap()
                        }
                    }
                }
            }
            87 -> {
                if (y - 1 < 0) {
                    error("You can not left the field")
                } else {
                    if (map[Pair(x, y - 1)] == WALL) {
                        error("You can not move through the wall")
                    } else {
                        if (checkWin(x, y - 1)) {
                            map[Pair(x, y)] = EMPTY
                            map[Pair(x, y - 1)] = PLAYER
                            playerPosition = Pair(x, y - 1)
                            state = GAME_FINISHED
                            notifyListeners()
                            printMap()
                        } else {
                            map[Pair(x, y)] = EMPTY
                            map[Pair(x, y - 1)] = PLAYER
                            playerPosition = Pair(x, y - 1)
                            notifyListeners()
                            printMap()
                        }
                    }
                }
            }
            119 -> {
                if (y - 1 < 0) {
                    error("You can not left the field")
                } else {
                    if (map[Pair(x, y - 1)] == WALL) {
                        error("You can not move through the wall")
                    } else {
                        if (checkWin(x, y - 1)) {
                            map[Pair(x, y)] = EMPTY
                            map[Pair(x, y - 1)] = PLAYER
                            playerPosition = Pair(x, y - 1)
                            state = GAME_FINISHED
                            notifyListeners()
                            printMap()
                        } else {
                            map[Pair(x, y)] = EMPTY
                            map[Pair(x, y - 1)] = PLAYER
                            playerPosition = Pair(x, y - 1)
                            notifyListeners()
                            printMap()
                        }
                    }
                }
            }
            83 -> {
                if (y + 1 > height) {
                    error("You can not left the field")
                } else {
                    if (map[Pair(x, y + 1)] == WALL) {
                        error("You can not move through the wall")
                    } else {
                        if (checkWin(x, y + 1)) {
                            map[Pair(x, y)] = EMPTY
                            map[Pair(x, y + 1)] = PLAYER
                            playerPosition = Pair(x, y + 1)
                            state = GAME_FINISHED
                            notifyListeners()
                            printMap()
                        } else {
                            map[Pair(x, y)] = EMPTY
                            map[Pair(x, y + 1)] = PLAYER
                            playerPosition = Pair(x, y + 1)
                            notifyListeners()
                            printMap()
                        }
                    }
                }
            }
            115 -> {
                if (y + 1 > height) {
                    error("You can not left the field")
                } else {
                    if (map[Pair(x, y + 1)] == WALL) {
                        error("You can not move through the wall")
                    } else {
                        if (checkWin(x, y + 1)) {
                            map[Pair(x, y)] = EMPTY
                            map[Pair(x, y + 1)] = PLAYER
                            playerPosition = Pair(x, y + 1)
                            state = GAME_FINISHED
                            notifyListeners()
                            printMap()
                        } else {
                            map[Pair(x, y)] = EMPTY
                            map[Pair(x, y + 1)] = PLAYER
                            playerPosition = Pair(x, y + 1)
                            notifyListeners()
                            printMap()
                        }
                    }
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

    private fun readMap(): Map<Pair<Int, Int>, Cell> {
        val file = File("labyrinth.txt")
        val list = file.readLines()
        val map = mutableMapOf<Pair<Int, Int>, Cell>()
        length = list.component1().length
        height = list.lastIndex
        for (y in 0..height) {
            for (x in 0 until length) {
                map[Pair(x, y)] = when (list[y][x]) {
                    '#' -> WALL
                    '-' -> EMPTY
                    'P' -> PLAYER
                    'E' -> EXIT
                    else -> WALL
                }
                if (list[y][x] == 'P') {
                    playerPosition = Pair(x, y)
                }
            }
        }
        return map
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