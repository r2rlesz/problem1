package pl.alsol.problem1

import java.util.*
import kotlin.collections.ArrayList
import kotlin.system.measureTimeMillis

fun main() {
    println("Number of logical cores: ${Runtime.getRuntime().availableProcessors()}")

    print("Preparing data...")
    val smallStreet = LinkedList(listOf(1,2,3,1))
    val largeStreet = ArrayList<Int>()
    generateSequence(0) { it + 1 }.take(40_300_000).toCollection(largeStreet)

    val smallStreetRun = arrayListOf(
        Run(RobTool::robHouses, "RobTool::robHouses"),
        Run(RobTool::robHousesAlt, "RobTool::robHousesAlt"),
        Run(RobTool::robHousesAlt2, "RobTool::robHousesAlt2"),
        Run(RobTool::robHousesAlt3, "RobTool::robHousesAlt3"),
    )

    val largeStreetNumberOfRuns = 3
    val largeStreetRun = arrayListOf<Run<ArrayList<Int>>>(
        Run(RobTool::robHouses, "RobTool::robHouses"),
        Run(RobTool::robHousesAlt, "RobTool::robHousesAlt"),
        Run(RobTool::robHousesAlt2, "RobTool::robHousesAlt2"),
        Run(RobTool::robHousesAlt3, "RobTool::robHousesAlt3"),
        Run(RobToolForArrayList::robHouses, "RobToolForArrayList::robHouses"),
        Run(RobToolForArrayList::robHousesAlt, "RobToolForArrayList::robHousesAlt"),
        Run(RobToolForArrayList::robHousesWithCompetitor, "RobToolForArrayList::robHousesWithCompetitor"),
        Run(RobToolForArrayList::robHousesWithCompetitorAlt, "RobToolForArrayList::robHousesWithCompetitorAlt"),
        Run(RobToolForArrayList::robHousesWithCompetitorAlt2, "RobToolForArrayList::robHousesWithCompetitorAlt2"),
        Run(RobToolForArrayList::robHousesWithCompetitorAndAccomplices, "RobToolForArrayList::robHousesWithCompetitorAndAccomplices"),
        Run(RobToolForArrayList::robHousesWithCompetitorAndAccomplicesAlt, "RobToolForArrayList::robHousesWithCompetitorAndAccomplicesAlt"),
    )

    System.gc()
    println("done.")

    println("\n-- Performing computations for small list (1,2,3,1) --")
    smallStreetRun.forEach { run ->
        run.performRobFunctionAndMeasure(smallStreet)
    }

    println("--------------------------------------------------------------------------")

    println("-- Performing computations for large list (${largeStreet.size} elements) --")
    for (runNo in 1..largeStreetNumberOfRuns) {
        println("\n---------- Run number $runNo --------------")
        largeStreetRun.forEach { run ->
            run.performRobFunctionAndMeasure(largeStreet)
        }
    }

    println("\n---------- Average compute times -----------")
    largeStreetRun.forEach { run ->
        println("${run.robFunctionName}(): ${(run.accumulatedTime ?: 0) / largeStreetNumberOfRuns} ms")
    }
}

data class Run<T> (
    val robFunction: (T) -> Long,
    val robFunctionName: String,
) {
    var lastMeasuredTime: Long? = null
        private set
    var accumulatedTime: Long? = null
        private set
    var lastResult: Long? = null
        private set

    fun performRobFunctionAndMeasure(houses: T) {
        println("Performing ${robFunctionName}()...")
        lastMeasuredTime = measureTimeMillis {
            lastResult = robFunction(houses)
        }
        accumulatedTime = (accumulatedTime ?: 0) + lastMeasuredTime!!
        println("result: $lastResult")
        println("Compute time: $lastMeasuredTime ms\n")
    }
}
