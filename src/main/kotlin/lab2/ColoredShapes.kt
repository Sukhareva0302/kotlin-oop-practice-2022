package lab2

import kotlin.math.PI
import kotlin.math.sqrt

data class Color(
    val transparency: Int, val red: Int, val green: Int, val blue: Int
) {
    init {
        require(
            transparency in 0..100 && red in 0..255 && green in 0..255 && blue in 0..255
        ) { "Invalid argument" }
    }
}

interface Shape2d {
    fun calcArea(): Double
}

interface ColoredShape2d : Shape2d {
    val borderColor: Color
    val fillColor: Color
}

data class Circle2d(
    override val borderColor: Color,
    override val fillColor: Color,
    val radius: Double
) :
    ColoredShape2d {
    init {
        require(radius > 0) {
            "Invalid argument"
        }
    }

    override fun calcArea(): Double {
        return radius * radius * PI
    }
}

data class Square2d(
    override val borderColor: Color,
    override val fillColor: Color,
    val side: Double
) :
    ColoredShape2d {
    init {
        require(side > 0) {
            "Invalid argument"
        }
    }

    override fun calcArea(): Double {
        return side * side
    }
}

data class Rectangle2d(
    override val borderColor: Color,
    override val fillColor: Color,
    val sideA: Double,
    val sideB: Double
) :
    ColoredShape2d {
    init {
        require(sideA > 0 && sideB > 0) {
            "Invalid argument"
        }
    }

    override fun calcArea(): Double {
        return sideA * sideB
    }
}

data class Triangle2d(
    override val borderColor: Color,
    override val fillColor: Color,
    val sideA: Double,
    val sideB: Double,
    val sideC: Double
) :
    ColoredShape2d {
    init {
        require(sideA > 0 && sideB > 0 && sideC > 0 && sideA + sideB > sideC && sideA + sideC > sideB && sideB + sideC > sideA) {
            "Invalid argument"
        }
    }

    override fun calcArea(): Double {
        val p = (sideA + sideB + sideC) / 2 //p - a commonly used name for the semi-perimeter constant
        return sqrt(p * (p - sideA) * (p - sideB) * (p - sideC))
    }
}