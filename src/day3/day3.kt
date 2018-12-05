package day3

import java.io.File

data class Claim(val id: Int, val atX: Int, val atY: Int, val width: Int, val height: Int) {
    fun toRectangel() = Rectangle(Point(atX, atY), Point(atX + width, atY + height))
}

fun main(args: Array<String>) {
    val input = File("input.txt").useLines { it.toList() }
    val regex = Regex("^#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)$")
    val claims = input.map {
        val res = regex.matchEntire(it)?.groupValues
        if (res == null) {
            null
        } else {
            val inted = res.subList(1, res.size).map { x -> x.toInt() }
            Claim(inted[0], inted[1], inted[2], inted[3], inted[4])
        }
    }

    val firstRound = intersections(claims.mapNotNull { it?.toRectangel() })
    val secondRound = intersections(firstRound)

    val firstRoundArea = firstRound.map { it.area() }.sum()
    val secondRoundArea = secondRound.map { it.area() }.sum()
    print("Part 1: ")
    println(firstRoundArea - secondRoundArea)
}

fun intersections(search: List<Rectangle>): List<Rectangle> {
    val output = mutableListOf<Rectangle>()
    for ((ndx, rect1) in search.withIndex()) {
        for (rect2 in search.subList(ndx + 1, search.size)) {
            val intersect = rect1.intersection(rect2)
            if (intersect != null) {
                output.add(intersect)
            }
        }
    }
    return output
}