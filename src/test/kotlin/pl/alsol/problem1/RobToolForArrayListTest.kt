package pl.alsol.problem1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.collections.ArrayList

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class RobToolForArrayListTest {

    private val functions = listOf(
        RobToolForArrayList::robHouses,
        RobToolForArrayList::robHousesAlt,
        RobToolForArrayList::robHousesAlt2,
        RobToolForArrayList::robHousesWithCompetitor,
        RobToolForArrayList::robHousesWithCompetitorAlt,
        RobToolForArrayList::robHousesWithCompetitorAlt2,
        RobToolForArrayList::robHousesWithCompetitorAndAccomplices,
        RobToolForArrayList::robHousesWithCompetitorAndAccomplicesAlt
    )

    @Test
    fun `Empty list should return 0`() {
        val empty = arrayListOf<Int>()
        performTest(empty, 0)
    }

    @Test
    fun `List of even number elements (1,2,3,1) should return 4`() {
        val houses = arrayListOf(1,2,3,1)
        performTest(houses, 4)
    }

    @Test
    fun `List of odd number elements (1,2,3,1,3) should return 7`() {
        val houses = arrayListOf(1,2,3,1,3)
        performTest(houses, 7)
    }

    @Test
    fun `Large list should return 7022518550012`() {
        val houses = ArrayList<Int>()
        generateSequence(0) { it + 1 }.take(5_300_007).toCollection(houses)
        performTest(houses, 7022518550012)
    }

    private fun performTest(input: ArrayList<Int>, expectedValue: Long) {
        val results = functions.map { func -> func(input) }.toLongArray()
        val expected = LongArray(functions.size) { expectedValue }
        val wrongResults = results
            .mapIndexed { i, result -> if (result != expected[i]) functions[i].name else null }
            .mapNotNull { it }
            .joinToString(", ")

        assertArrayEquals(expected, results, "Functions \"${wrongResults}\" should return $expectedValue")
    }
}
