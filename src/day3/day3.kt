package day3

import java.io.File

data class Claim(val id: Int, val atX: Int, val atY: Int, val width: Int, val height: Int)

fun main(args: Array<String>) {
    val input = File("test.txt").useLines { it.toList() }
    val regex = Regex("^#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)$")
    val claims = input.map {
        val res = regex.matchEntire(it)?.groupValues
        if(res == null) {
            null
        }
        else {
            val inted = res.subList(1, res.size).map { x -> x.toInt() }
            Claim(inted[0], inted[1], inted[2], inted[3], inted[4])
        }
    }
    println(claims.joinToString("\n"))
}