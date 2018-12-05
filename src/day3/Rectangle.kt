package day3

import kotlin.math.max
import kotlin.math.min

data class Point(val x: Int, val y: Int)

data class Rectangle (val topLeft: Point, val bottomRight: Point){
    fun intersection(other: Rectangle): Rectangle? {
        val rect = Rectangle(
            Point(
                max(topLeft.x, other.topLeft.x),
                max(topLeft.y, other.topLeft.y)
            ),
            Point(
                min(bottomRight.x, other.bottomRight.y),
                min(bottomRight.y, other.bottomRight.y)
            )
        )
        if(!rect.valid()) return null
        return rect
    }

    fun area() = (bottomRight.x - topLeft.x) * (bottomRight.y - topLeft.y)

    fun valid() = topLeft.x < bottomRight.x && topLeft.y < bottomRight.y
}