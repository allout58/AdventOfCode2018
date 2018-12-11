package day5

import java.io.File

fun main(args: Array<String>) {
    val input = File("input.txt").useLines { it.toList() }[0].toMutableList()

    print("Day 1: ")
    println(react(input).size)

    val distinctTypes = input.map { it.toLowerCase() }.toSet()
    val typeLength = mutableMapOf<Char, Int>()
    for (type in distinctTypes) {
        // Remove all of "type"
        typeLength[type] = react(input.filter { it.toLowerCase() != type }).size
    }
    println(typeLength)

    print("Day 2: ")
    println(typeLength.entries.sortedBy { it.value }[0])
}

private fun react(input: List<Char>): List<Char> {
    var previous: Char?
    var madeChanges: Boolean

    val list = mutableListOf<Char>()
    list.addAll(input)
    val nextLlist = mutableListOf<Char>()
//    println(list.joinToString(""))

    do {
        madeChanges = false
        previous = null
        for (char in list) {
            if (previous != null) {
                if (
                // Same character
                    previous.toLowerCase() == char.toLowerCase() &&
                    // Different cases
                    ((previous.isLowerCase() && char.isUpperCase()) || (previous.isUpperCase() && char.isLowerCase()))
                ) {
                    previous = null
                    madeChanges = true
                } else {
                    nextLlist.add(previous)
                    previous = char
                }
            } else {
                previous = char
            }
        }
        if (previous != null) {
            nextLlist.add(previous)
        }
        list.clear()
        list.addAll(nextLlist)
//        println(nextLlist.joinToString(""))
        nextLlist.clear()
    } while (madeChanges)
    return list
}