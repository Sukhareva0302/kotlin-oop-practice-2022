package lab4

import java.io.File
import lab4.Cell.*

fun readMap(): Model {
    val file = File("labyrinth.txt")
    val list = file.readLines()
    val map = mutableMapOf<Pair<Int, Int>, Cell>()
    val length = list.component1().length
    val height = list.lastIndex
    for (y in 0..height) {
        for (x in 0 until length) {
            map[Pair(x, y)] = when (list[y][x]) {
                '#' -> WALL
                '-' -> EMPTY
                'P' -> PLAYER
                'E' -> EXIT
                else -> WALL
            }
        }
    }
    return Model(height, length, map)
}