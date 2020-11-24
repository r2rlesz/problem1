package pl.alsol.problem1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.util.*

internal class RobTest {

    @Test
    fun `Empty list should return 0`() {
        val empty = emptyList<Int>()
        assertEquals(0, rob(empty), "Empty list should return 0.")
    }

    @Test
    fun `One element list should return 666`() {
        val element = 666
        assertEquals(666, rob(listOf(element)), "List with only one element of value: $element should return that element value.")
    }

    @Test
    fun `Two element list of 1 and 10 should return 10`() {
        val houses = listOf(1, 10)
        assertEquals(10, rob(houses), "Two element list of 1 and 10 should return 10.")
    }

    @Test
    fun `List of 1,2,3,1 should return 4`() {
        val houses = listOf(1,2,3,1)
        assertEquals(4, rob(houses), "List of 1,2,3,1 should return 4.")
    }

    @Test
    fun `List of 100, 20, 100, 20, 100, 1000 should return 1200`() {
        val houses = listOf(100,20,100,20,100,1000)
        assertEquals(1200, rob(houses), "List of 1,2,3,1 should return 1200.")
    }

}
