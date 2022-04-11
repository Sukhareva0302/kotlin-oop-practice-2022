package lab2

class ShapeCollector {
    val shapeList = mutableListOf<ColoredShape2d>()
    fun add(shape2d: ColoredShape2d) {
        shapeList.add(shape2d)
    }

    fun minArea(): ColoredShape2d? {
        return shapeList.minByOrNull { it.calcArea() }
    }

    fun maxArea(): ColoredShape2d? {
        return shapeList.maxByOrNull { it.calcArea() }
    }

    fun sumOfArea(): Double {
        return shapeList.sumOf { it.calcArea() }
    }

    fun getAllWithBorderColor(colorFilter: Color): List<ColoredShape2d> {
        return shapeList.filter { it.borderColor == colorFilter }
    }

    fun getAllWithFillColor(colorFilter: Color): List<ColoredShape2d> {
        return shapeList.filter { it.fillColor == colorFilter }
    }

    fun getAll(): List<ColoredShape2d> {
        return shapeList
    }

    fun size(): Int {
        return shapeList.size
    }

    fun groupByBorderColor(): Map<Color, List<ColoredShape2d>> {
        return shapeList.groupBy { it.borderColor }
    }

    fun groupByFillColor(): Map<Color, List<ColoredShape2d>> {
        return shapeList.groupBy { it.fillColor }
    }

    inline fun <reified T> getSpecificShape(): List<ColoredShape2d> {
        return shapeList.filter { it is T }
    } //idea from:
    // https://kotlinlang.ru/docs/reference/typecasts.html
    // https://kotlinlang.ru/docs/reference/inline-functions.html#reified-type-parameters
}