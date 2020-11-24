# Problem 1

## Description
You are a professional robber planning to rob houses along a street. Each house has a certain
amount of money stashed, the only constraint stopping you from robbing each of them is that
adjacent houses have security systems connected and it will automatically contact the police
if two adjacent houses were broken into on the same night .
Write a function call that takes a list of non-negative integers representing the amount of money
of each house, return the maximum amount of money you can rob tonight without alerting the
police .
Example

Input: Java.util.Vector of [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3). Total amount you
can rob = 1 + 3 = 4.

## Implementation
### Comment
In this task I wrote multiple versions of the same function ( some are Multithreaded like RobToolForArrayList::robHousesWithCompetitorAndAccomplices). Comments are inside code.

### Build instructions
Requirements: *Java 1.8*

Inside project main directory:
To compile: *./gradlew compileKotlin*
To run tests: *./gradlew test*
To run application: *./gradlew run*

### Console log:
Number of logical cores: 8
Preparing data...done.

-- Performing computations for small list (1,2,3,1) --
Performing RobTool::robHouses()...
result: 4
Compute time: 1 ms

Performing RobTool::robHousesAlt()...
result: 4
Compute time: 10 ms

Performing RobTool::robHousesAlt2()...
result: 4
Compute time: 4 ms

Performing RobTool::robHousesAlt3()...
result: 4
Compute time: 0 ms

--------------------------------------------------------------------------
-- Performing computations for large list (40300000 elements) --

---------- Run number 1 --------------
Performing RobTool::robHouses()...
result: 406022500000000
Compute time: 91 ms

Performing RobTool::robHousesAlt()...
result: 406022500000000
Compute time: 4615 ms

Performing RobTool::robHousesAlt2()...
result: 406022500000000
Compute time: 248 ms

Performing RobTool::robHousesAlt3()...
result: 406022500000000
Compute time: 297 ms

Performing RobToolForArrayList::robHouses()...
result: 406022500000000
Compute time: 121 ms

Performing RobToolForArrayList::robHousesAlt()...
result: 406022500000000
Compute time: 298 ms

Performing RobToolForArrayList::robHousesWithCompetitor()...
result: 406022500000000
Compute time: 139 ms

Performing RobToolForArrayList::robHousesWithCompetitorAlt()...
result: 406022500000000
Compute time: 91 ms

Performing RobToolForArrayList::robHousesWithCompetitorAlt2()...
result: 406022500000000
Compute time: 291 ms

Performing RobToolForArrayList::robHousesWithCompetitorAndAccomplices()...
result: 406022500000000
Compute time: 61 ms

Performing RobToolForArrayList::robHousesWithCompetitorAndAccomplicesAlt()...
result: 406022500000000
Compute time: 71 ms


---------- Run number 2 --------------
Performing RobTool::robHouses()...
result: 406022500000000
Compute time: 85 ms

Performing RobTool::robHousesAlt()...
result: 406022500000000
Compute time: 256 ms

Performing RobTool::robHousesAlt2()...
result: 406022500000000
Compute time: 226 ms

Performing RobTool::robHousesAlt3()...
result: 406022500000000
Compute time: 316 ms

Performing RobToolForArrayList::robHouses()...
result: 406022500000000
Compute time: 122 ms

Performing RobToolForArrayList::robHousesAlt()...
result: 406022500000000
Compute time: 299 ms

Performing RobToolForArrayList::robHousesWithCompetitor()...
result: 406022500000000
Compute time: 76 ms

Performing RobToolForArrayList::robHousesWithCompetitorAlt()...
result: 406022500000000
Compute time: 92 ms

Performing RobToolForArrayList::robHousesWithCompetitorAlt2()...
result: 406022500000000
Compute time: 245 ms

Performing RobToolForArrayList::robHousesWithCompetitorAndAccomplices()...
result: 406022500000000
Compute time: 64 ms

Performing RobToolForArrayList::robHousesWithCompetitorAndAccomplicesAlt()...
result: 406022500000000
Compute time: 63 ms


---------- Run number 3 --------------
Performing RobTool::robHouses()...
result: 406022500000000
Compute time: 76 ms

Performing RobTool::robHousesAlt()...
result: 406022500000000
Compute time: 268 ms

Performing RobTool::robHousesAlt2()...
result: 406022500000000
Compute time: 225 ms

Performing RobTool::robHousesAlt3()...
result: 406022500000000
Compute time: 204 ms

Performing RobToolForArrayList::robHouses()...
result: 406022500000000
Compute time: 104 ms

Performing RobToolForArrayList::robHousesAlt()...
result: 406022500000000
Compute time: 291 ms

Performing RobToolForArrayList::robHousesWithCompetitor()...
result: 406022500000000
Compute time: 66 ms

Performing RobToolForArrayList::robHousesWithCompetitorAlt()...
result: 406022500000000
Compute time: 69 ms

Performing RobToolForArrayList::robHousesWithCompetitorAlt2()...
result: 406022500000000
Compute time: 222 ms

Performing RobToolForArrayList::robHousesWithCompetitorAndAccomplices()...
result: 406022500000000
Compute time: 45 ms

Performing RobToolForArrayList::robHousesWithCompetitorAndAccomplicesAlt()...
result: 406022500000000
Compute time: 44 ms


---------- Average compute times -----------
RobTool::robHouses(): 84 ms
RobTool::robHousesAlt(): 1713 ms
RobTool::robHousesAlt2(): 233 ms
RobTool::robHousesAlt3(): 272 ms
RobToolForArrayList::robHouses(): 115 ms
RobToolForArrayList::robHousesAlt(): 296 ms
RobToolForArrayList::robHousesWithCompetitor(): 93 ms
RobToolForArrayList::robHousesWithCompetitorAlt(): 84 ms
RobToolForArrayList::robHousesWithCompetitorAlt2(): 252 ms
RobToolForArrayList::robHousesWithCompetitorAndAccomplices(): 56 ms
RobToolForArrayList::robHousesWithCompetitorAndAccomplicesAlt(): 59 ms
