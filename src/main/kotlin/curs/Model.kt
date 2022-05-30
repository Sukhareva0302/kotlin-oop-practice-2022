package curs

class Cell(var isAlive: Boolean = false) {
    val neighbors: MutableList<Cell> = arrayListOf()

    fun evolveCell(livingNeighborsCount: Int = neighbors.count { it.isAlive }) {
        isAlive = when (livingNeighborsCount) {
            0, 1 -> false
            2 -> isAlive
            3 -> true
            else -> false
        }
    }

    fun live() {
        isAlive = true
    }

    fun dead() {
        isAlive = false
    }
}

interface ModelChangeListener {
    fun onModelChanged()
}

class Universe(private val size: Int) {
    private val cells = Array(size * size) { Cell() }
    private val listeners: MutableSet<ModelChangeListener> = mutableSetOf()

    init {
        cells.forEachIndexed { i, cell -> cell.setupNeighbors(i) }
        //Connecting the sides:
        for (i in 1 until size - 1) {
            if (!cellAt(0, i).neighbors.contains(cellAt(size - 1, i))) {
                cellAt(0, i).neighbors.add(cellAt(size - 1, i))
            }
            if (!cellAt(0, i).neighbors.contains(cellAt(size - 1, i - 1))) {
                cellAt(0, i).neighbors.add(cellAt(size - 1, i - 1))
            }
            if (!cellAt(0, i).neighbors.contains(cellAt(size - 1, i + 1))) {
                cellAt(0, i).neighbors.add(cellAt(size - 1, i + 1))
            }
            if (!cellAt(i, 0).neighbors.contains(cellAt(i, size - 1))) {
                cellAt(i, 0).neighbors.add(cellAt(i, size - 1))
            }
            if (!cellAt(i, 0).neighbors.contains(cellAt(i - 1, size - 1))) {
                cellAt(i, 0).neighbors.add(cellAt(i - 1, size - 1))
            }
            if (!cellAt(i, 0).neighbors.contains(cellAt(i + 1, size - 1))) {
                cellAt(i, 0).neighbors.add(cellAt(i + 1, size - 1))
            }
            if (!cellAt(size - 1, i).neighbors.contains(cellAt(0, i))) {
                cellAt(size - 1, i).neighbors.add(cellAt(0, i))
            }
            if (!cellAt(size - 1, i).neighbors.contains(cellAt(0, i - 1))) {
                cellAt(size - 1, i).neighbors.add(cellAt(0, i - 1))
            }
            if (!cellAt(size - 1, i).neighbors.contains(cellAt(0, i + 1))) {
                cellAt(size - 1, i).neighbors.add(cellAt(0, i + 1))
            }
            if (!cellAt(i, size - 1).neighbors.contains(cellAt(i, 0))) {
                cellAt(i, size - 1).neighbors.add(cellAt(i, 0))
            }
            if (!cellAt(i, size - 1).neighbors.contains(cellAt(i - 1, 0))) {
                cellAt(i, size - 1).neighbors.add(cellAt(i - 1, 0))
            }
            if (!cellAt(i, size - 1).neighbors.contains(cellAt(i + 1, 0))) {
                cellAt(i, size - 1).neighbors.add(cellAt(i + 1, 0))
            }
        }
        //Connecting the corners:
        //upper left corner
        if (!cellAt(0, 0).neighbors.contains(cellAt(size - 1, size - 1))) {
            cellAt(0, 0).neighbors.add(cellAt(size - 1, size - 1))
        }
        if (!cellAt(0, 0).neighbors.contains(cellAt(size - 1, 0))) {
            cellAt(0, 0).neighbors.add(cellAt(size - 1, 0))
        }
        if (!cellAt(0, 0).neighbors.contains(cellAt(size - 1, 1))) {
            cellAt(0, 0).neighbors.add(cellAt(size - 1, 1))
        }
        if (!cellAt(0, 0).neighbors.contains(cellAt(0, size - 1))) {
            cellAt(0, 0).neighbors.add(cellAt(0, size - 1))
        }
        if (!cellAt(0, 0).neighbors.contains(cellAt(1, size - 1))) {
            cellAt(0, 0).neighbors.add(cellAt(1, size - 1))
        }

        //lower right corner
        if (!cellAt(size - 1, size - 1).neighbors.contains(cellAt(0, 0))) {
            cellAt(size - 1, size - 1).neighbors.add(cellAt(0, 0))
        }
        if (!cellAt(size - 1, size - 1).neighbors.contains(cellAt(0, size - 1))) {
            cellAt(size - 1, size - 1).neighbors.add(cellAt(0, size - 1))
        }
        if (!cellAt(size - 1, size - 1).neighbors.contains(cellAt(0, size - 2))) {
            cellAt(size - 1, size - 1).neighbors.add(cellAt(0, size - 2))
        }
        if (!cellAt(size - 1, size - 1).neighbors.contains(cellAt(size - 1, 0))) {
            cellAt(size - 1, size - 1).neighbors.add(cellAt(size - 1, 0))
        }
        if (!cellAt(size - 1, size - 1).neighbors.contains(cellAt(size - 2, 0))) {
            cellAt(size - 1, size - 1).neighbors.add(cellAt(size - 2, 0))
        }

        //lower left corner
        if (!cellAt(0, size - 1).neighbors.contains(cellAt(size - 1, 0))) {
            cellAt(0, size - 1).neighbors.add(cellAt(size - 1, 0))
        }
        if (!cellAt(0, size - 1).neighbors.contains(cellAt(size - 1, size - 1))) {
            cellAt(0, size - 1).neighbors.add(cellAt(size - 1, size - 1))
        }
        if (!cellAt(0, size - 1).neighbors.contains(cellAt(size - 1, size - 2))) {
            cellAt(0, size - 1).neighbors.add(cellAt(size - 1, size - 2))
        }
        if (!cellAt(0, size - 1).neighbors.contains(cellAt(0, 0))) {
            cellAt(0, size - 1).neighbors.add(cellAt(0, 0))
        }
        if (!cellAt(0, size - 1).neighbors.contains(cellAt(1, 0))) {
            cellAt(0, size - 1).neighbors.add(cellAt(1, 0))
        }

        //upper right corner
        if (!cellAt(size - 1, 0).neighbors.contains(cellAt(size - 1, size - 1))) {
            cellAt(size - 1, 0).neighbors.add(cellAt(size - 1, size - 1))
        }
        if (!cellAt(size - 1, 0).neighbors.contains(cellAt(0, size - 1))) {
            cellAt(size - 1, 0).neighbors.add(cellAt(0, size - 1))
        }
        if (!cellAt(size - 1, 0).neighbors.contains(cellAt(size - 2, size - 1))) {
            cellAt(size - 1, 0).neighbors.add(cellAt(size - 2, size - 1))
        }
        if (!cellAt(size - 1, 0).neighbors.contains(cellAt(0, 0))) {
            cellAt(size - 1, 0).neighbors.add(cellAt(0, 0))
        }
        if (!cellAt(size - 1, 0).neighbors.contains(cellAt(0, 1))) {
            cellAt(size - 1, 0).neighbors.add(cellAt(0, 1))
        }
    }

    fun addModelChangeListener(listener: ModelChangeListener) {
        listeners.add(listener)
    }

    fun removeModelChangeListener(listener: ModelChangeListener) {
        listeners.remove(listener)
    }

    private fun notifyListeners() {
        listeners.forEach { it.onModelChanged() }
    }

    fun evolve() {
        val livingNeighborCounts = cells.map { it.neighbors.count { it.isAlive } }
        cells.forEachIndexed { i, cell -> cell.evolveCell(livingNeighborCounts[i]) }
        notifyListeners()
    }

    private fun Cell.setupNeighbors(index: Int) {
        neighbors.apply {
            neighborCoordinatesOf(index.toX(), index.toY())
                .filter { it.isInBounds() }.map { it.toIndex() }
                .forEach { add(cells[it]) }
        }
    }

    private fun neighborCoordinatesOf(x: Int, y: Int) = arrayOf(
        Pair(x - 1, y - 1), Pair(x, y - 1), Pair(x + 1, y - 1), Pair(x - 1, y),
        Pair(x + 1, y), Pair(x - 1, y + 1), Pair(x, y + 1), Pair(x + 1, y + 1)
    )

    private fun Pair<Int, Int>.isInBounds() = !((first < 0).or(first >= size).or(second < 0).or(second >= size))
    private fun Pair<Int, Int>.toIndex() = second * size + first
    private fun Int.toX() = this % size
    private fun Int.toY() = this / size

    fun cellAt(x: Int, y: Int): Cell {
        return cells[Pair(x, y).toIndex()]
    }

    /*Print model, useful for debugging and tests
    fun print() {
        for (y in 0 until size) {
            for (x in 0 until size) {
                if (cellAt(x, y).isAlive) {
                    print(1)
                } else {
                    print(0)
                }
            }
            println()
        }
    }*/
}