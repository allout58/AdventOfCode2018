package day3

import java.io.File

data class Claim(val id: Int, val atX: Int, val atY: Int, val width: Int, val height: Int) {
    fun toRectangel() = Rectangle(Point(atX, atY), Point(atX + width, atY + height))
}

fun main(args: Array<String>) {
    val input = File("test.txt").useLines { it.toList() }
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

    val sparseTable = HashMap<Int, HashMap<Int, Int>>()

    for (claim in claims.filterNotNull()) {
        for (x in claim.atX until (claim.atX + claim.width)) {
            val row = sparseTable.getOrDefault(x, HashMap())
            for (y in claim.atY until (claim.atY + claim.height)) {
                val oldValue = row.getOrDefault(y, 0)
                row[y] = oldValue + 1
            }
            sparseTable[x] = row
        }
    }

    var count = 0
    for (row in sparseTable) {
        for (column in row.value) {
            if (column.value > 1) {
                count++
            }
        }
    }

    print("Day 1: ")
    println(count)

    print("Day 2: ")
    val rects = claims.filterNotNull().map { it.toRectangel() }.toMutableList()
    val intersected = mutableListOf<Rectangle>();

    for (rect in rects) {
        if (rect in intersected) {
            continue
        }
        for (rect2 in rects) {
            if (rect == rect2) {
                continue
            }
            if (rect2 in intersected) {
                continue
            }
            if (rect.intersection(rect2) == null) {
                intersected.add(rect)
                intersected.add(rect2)
            }
        }
    }

    val notIntersected = rects.find { it !in intersected }

    if (notIntersected != null) {
        println(claims.filterNotNull().find {
            it.atX == notIntersected.topLeft.x
                    && it.atY == notIntersected.topLeft.y
                    && it.atX + it.width == notIntersected.bottomRight.x
                    && it.atY + it.height == notIntersected.bottomRight.y
        })
    }
}
