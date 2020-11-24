package pl.alsol.problem1

import kotlin.math.max

/**
    General RobTool for Lists
 */
object RobTool {
    /**
     * Robs houses in single thread - fastest
     *
     */
    fun robHouses(houses: List<Int>): Long {
        var even = 0L
        var odd = 0L
        var isEven = true
        for (house in houses) {
            if (isEven) {
                even += house.toLong()
            } else {
                odd += house.toLong()
            }
            isEven = !isEven
        }
        return max(even, odd)
    }

    /**
     * More elegant version but slower because of second array (result)
     */
    fun robHousesAlt(houses: List<Int>): Long {
        val result = arrayOf(0L, 0L)
        var parity = 0
        for (house in houses) {
            result[parity] += house.toLong()
            parity = parity xor 1
        }
        return result.maxOrNull()!!
    }

    /**
     * Iterates twice so slower than in [robHouses]
     */
    fun robHousesAlt2(houses: List<Int>): Long {
        return (0..1).map { parityIndex ->
            houses.foldIndexed(0L) {i, acc, house ->
                // ignores every second element (multiplication by 0 for every second element) - returns acc
                // for parityIndex = 0 takes indexes 1, 3, 5... ; for parityIndex = 1 takes 0, 2, 4...
                acc + house.toLong() * ((i % 2) xor parityIndex)
            }
        }.maxOrNull()!! //can't be null
    }

    /**
     * Iterates twice so slower than in robHouses()
     * Almost the same as [robHousesAlt2]
     */
    fun robHousesAlt3(houses: List<Int>): Long {
        return (0..1).map { parityIndex ->
            // faster than: houses.asSequence().filterIndexed { i, _ -> i % 2 == parityIndex }.map { it.toLong() }.sum()
            // or: houses.asSequence().mapIndexed { i, house -> if (parityIndex == i % 2) house.toLong() else 0L }.sum()
            houses.foldIndexed(0L) {i, acc, house ->
                if (parityIndex == i % 2)
                    acc + house.toLong()
                else
                    acc
            }
        }.maxOrNull()!! //can't be null
    }
}

