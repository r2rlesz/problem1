package pl.alsol.problem1

import java.util.*
import kotlin.math.max
import kotlin.system.measureTimeMillis

/**
 * [houses] should not contain negative numbers
 * Returns maximum possible sum of not adjacent elements
 */
fun rob(houses: List<Int>): Long {
    // In case where implementation of List get function have complexity of O(n) where n is number of elements in list
    // like in LinkedList, we should iterate over elements
    val iterator = houses.iterator()
    if (!iterator.hasNext())
        return 0L

    var prev = iterator.next().toLong()
    if (!iterator.hasNext())
        return prev

    var cur = max(prev, iterator.next().toLong())
    while (iterator.hasNext()) {
        val tmp = cur
        cur = max(iterator.next() + prev, cur)
        prev = tmp
    }
    return cur
}

fun main() {
    print("Preparing data...")
    val smallStreet = LinkedList(listOf(1,2,3,1))
    val mediumStreet = listOf(100,20,100,20,100,1000)
    val largeStreet = LinkedList<Int>()
    generateSequence(0) { it + 1 }.take(1_333_333).toCollection(largeStreet)
    println("done.")

    for (street in listOf(smallStreet, mediumStreet, largeStreet)) {
        val streetDescription = if (street.size <= 20) street.joinToString(", ") else "with ${street.size} houses"
        println("\nRobbing street ($streetDescription)...")
        val streetCash: Long
        val time = measureTimeMillis {
            streetCash = rob(street)
        }
        println("Robbed money: $streetCash")
        println("Performed in $time ms")
    }
}
