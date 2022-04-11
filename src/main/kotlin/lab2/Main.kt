package lab2

fun main() {
    val shapes = ShapeCollector()
    shapes.add(Circle2d(Color(100, 100, 100, 100), Color(100, 100, 100, 100), 8.00))
    shapes.add(Circle2d(Color(8, 12, 17, 100), Color(100, 100, 100, 100), 10.00))
    shapes.add(Square2d(Color(0, 75, 15, 200), Color(100, 85, 17, 6), 10.00))
    shapes.add(Rectangle2d(Color(100, 100, 100, 100), Color(100, 85, 17, 6), 10.00, 15.00))
    shapes.add(Triangle2d(Color(5, 17, 2, 16), Color(7, 22, 18, 29), 3.00, 4.00, 5.00))
    println(shapes.minArea())
    println(shapes.maxArea())
    println(shapes.sumOfArea())
    println(shapes.getAllWithBorderColor(Color(100, 100, 100, 100)))
    println(shapes.getAllWithFillColor(Color(100, 85, 17, 6)))
    println(shapes.getAll())
    println(shapes.size())
    println(shapes.groupByBorderColor())
    println(shapes.groupByFillColor())
    println(shapes.getSpecificShape<Circle2d>())
}