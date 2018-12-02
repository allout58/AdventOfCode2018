package day2

import java.io.File

fun main(args: Array<String>) {
    val input = File("input.txt").useLines { it.toList() }

    var twos = 0
    var threes = 0

    for (line in input) {
        val map = mutableMapOf<Char, Int>()
        var hasTwos = false
        var hasThrees = false
        line.forEach { map.set(it, map.getOrDefault(it, 0) + 1) }
        for (value in map.values) {
            if(!hasTwos && value == 2) {
                hasTwos = true
                twos++
            }
            else if (!hasThrees && value == 3) {
                hasThrees = true
                threes++
            }
        }
    }
    print("Day 1: ")
    println(twos * threes)

}