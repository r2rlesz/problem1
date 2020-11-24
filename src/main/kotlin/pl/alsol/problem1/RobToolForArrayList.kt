package pl.alsol.problem1

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import java.util.stream.Stream
import kotlin.math.max
import kotlin.math.min

/**
 * RobToolForArrayList provides additional tweaks to algorithm but is only suitable for ArrayList
 * because of need of List's get function to have O(1) complexity
 */
object RobToolForArrayList {
    /**
     * Fastest in single thread
     */
    fun robHouses(houses: ArrayList<Int>): Long {
        var even = 0L
        var odd = 0L
        for (i in 0..houses.lastIndex) {
            if (i % 2 == 0) {
                even += houses[i]
            } else {
                odd += houses[i]
            }
        }
        return max(even, odd)
    }

    /**
     * More elegant version but slower
     */
    fun robHousesAlt(houses: ArrayList<Int>): Long {
        val result = arrayOf(0L, 0L)
        for (i in 0..houses.lastIndex) {
            result[i % 2] += houses[i].toLong()
        }
        return max(result[0], result[1])
    }

    /**
     * Alternative version to [robHouses] - slightly slower
     */
    fun robHousesAlt2(houses: ArrayList<Int>): Long {
        return (0..1).map { parityIndex ->
            (parityIndex..houses.lastIndex step 2).asSequence().map { i -> houses[i].toLong() }.sum()
        }.maxOrNull()!! //can't be null
    }

    /**
     * Runs in two parallel threads (even and odd indexes).
     * Suitable for larger lists (but performance boost is insignificant because of too simple computations on each element)
     */
    fun robHousesWithCompetitor(houses: ArrayList<Int>): Long = runBlocking {
        (0..1).map { parityIndex -> async(Dispatchers.Default) {
            var result = 0L
            for (i in parityIndex..houses.lastIndex step 2) {
                result += houses[i].toLong()
            }
            result
        } }.awaitAll().fold(0) { max, robbed -> max(max, robbed) }
    }

    /**
     * Runs in two parallel threads (two halves of list).
     * Suitable for larger lists (but performance boost is insignificant because of too simple computations on each element)
     */
    fun robHousesWithCompetitorAlt(houses: ArrayList<Int>): Long = runBlocking {
        if (houses.isEmpty()) {
            0L
        } else {
            var half = houses.size / 2
            half -= half % 2
            val result = listOf(0..houses.size / 2, houses.size / 2 + 1..houses.lastIndex).map { parityIndex -> async(Dispatchers.Default) {
                var even = 0L
                var odd = 0L
                for (i in parityIndex) {
                    if (i % 2 == 0) {
                        even += houses[i]
                    } else {
                        odd += houses[i]
                    }
                }
                Pair(even, odd)
            } }.awaitAll().reduce { sum, pair -> Pair(sum.first + pair.first, sum.second + pair.second) }

            max(result.first, result.second)
        }
    }

    /**
     * [robHousesWithCompetitor] alternative. Java parallel streams instead of coroutines.
     */
    fun robHousesWithCompetitorAlt2(houses: ArrayList<Int>): Long {
        return (0..1).toList().parallelStream().map { parityIndex ->
            (parityIndex..houses.lastIndex step 2).asSequence().map { i -> houses[i].toLong() }.sum()
        }.max(Long::compareTo).get() //can't be null
    }

    /**
     * Utilizes all cores.
     * Divides list to chunks which are directed to separate threads (number of thread equals number of available logical cores)
     * Then results from threads (sums) are summed again separately for even and odd numbers and returns largest sum from these two
     * Suitable for largest list
     * Fastest version
     */
    fun robHousesWithCompetitorAndAccomplices(houses: ArrayList<Int>): Long = runBlocking {
        when(val logicalCores = Runtime.getRuntime().availableProcessors()) {
            1 -> robHouses(houses)
            else -> {
                val halfOfLogicalCores = max(1, min(logicalCores / 2, houses.size / 2)) //not more than half of houses
                val intermediateChunkSize = houses.size / halfOfLogicalCores
                //chunkSize must be even
                val chunkSize = intermediateChunkSize + intermediateChunkSize % 2
                (0..1).map { parityIndex -> async(Dispatchers.Unconfined) {
                    (0 until halfOfLogicalCores).map { threadNo -> async(Dispatchers.Default) {
                        val startIndex = threadNo * chunkSize + parityIndex
                        //ensures that last index is included in concrete parity
                        val endIndex = if (threadNo == halfOfLogicalCores - 1) houses.lastIndex else startIndex + chunkSize - 1
                        //println("${Thread.currentThread().name};$chunkSize;$startIndex..$endIndex")
                        var result = 0L
                        for (i in startIndex..endIndex step 2) {
                            result += houses[i].toLong()
                        }
                        result
                    } }.awaitAll().sum()
                } }.awaitAll().fold(0L) { max, robbed -> max(max, robbed) }
            }
        }
    }

    /**
     * Same as [robHousesWithCompetitorAndAccomplices] but with parallel streams instead of coroutines.
     */
    fun robHousesWithCompetitorAndAccomplicesAlt(houses: ArrayList<Int>): Long {
        return when(val logicalCores = Runtime.getRuntime().availableProcessors()) {
            1 -> robHousesAlt(houses)
            else -> {
                val halfOfLogicalCores = max(1, min(logicalCores / 2, houses.size / 2))
                val intermediateChunkSize = houses.size / halfOfLogicalCores
                //chunkSize must be even
                val chunkSize = intermediateChunkSize + intermediateChunkSize % 2
                (0..1).toList().parallelStream().map { parityIndex ->
                    Stream.iterate(0) { it + 1 }
                        .limit(halfOfLogicalCores.toLong())
                        .parallel()
                        .map { threadNo ->
                            val startIndex = threadNo * chunkSize + parityIndex
                            //ensures that last index is included in concrete parity
                            val endIndex =
                                if (threadNo == halfOfLogicalCores - 1) houses.lastIndex else startIndex + chunkSize - 1
                            var result = 0L
                            for (i in startIndex..endIndex step 2) {
                                result += houses[i].toLong()
                            }
                            result
                            //alternatively for for loop (slightly slower):
                            //(startIndex..endIndex step 2).asSequence().map { i -> houses[i].toLong() }.sum()
                        }
                        .reduce(0L) { t, u -> t + u }
                }.max(Long::compareTo).get() //safe because it can't be null
            }
        }
    }

    /* //Too many thread switching
    fun robHousesWithCompetitorAndAccomplicesAlt(houses: List<Int>): Long {
        return (0..1).toList().parallelStream().map { parityIndex ->
            Stream.iterate(parityIndex) { it + 2 }
                .limit(houses.size.toLong() / 2 + if (parityIndex == 0) houses.size.toLong() % 2 else 0)
                .parallel()
                .map { i -> houses[i].toLong() }
                .reduce(0) { t, u -> t + u }
        }.max(Long::compareTo).get() //safe because it can't be null
    }
    */
}