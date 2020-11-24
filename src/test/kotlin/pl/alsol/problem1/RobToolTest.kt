package pl.alsol.problem1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class RobToolTest {

    private val functions = listOf(RobTool::robHouses, RobTool::robHousesAlt, RobTool::robHousesAlt2, RobTool::robHousesAlt3)

    @Test
    fun `Empty list should return 0`() {
        val empty = emptyList<Int>()
        functions.forEach { func ->
            assertEquals(0, func(empty), "${func.name} should return 0.")
        }
    }

    @Test
    fun `One element list should return 666`() {
        val houses = listOf(666)
        functions.forEach { func ->
            assertEquals(666, func(houses), "${func.name} should return 666.")
        }
    }

    @Test
    fun `List of 1,2,3,1 should return 4`() {
        val houses = LinkedList(listOf(1, 2, 3, 1))
        functions.forEach { func ->
            assertEquals(4, func(houses), "${func.name} should return 4.")
        }
    }
}
