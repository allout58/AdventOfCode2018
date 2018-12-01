package day1

import java.io.File

fun main(args: Array<String>) {
    val input = File("input.txt").useLines { it.toList() }.map { it.toInt() }
    print("Day 1: ")
    println(input.reduceRight { i, acc -> i + acc })

    print("Day 2: ")
    val frequencies = mutableListOf(0)
    var acc = 0
    var keepRunning = true
    while(keepRunning) {
        for (i in input) {
            acc += i
            if (frequencies.contains(acc)) {
                println(acc)
                keepRunning = false
                break
            }
            frequencies.add(acc)
        }
    }
}