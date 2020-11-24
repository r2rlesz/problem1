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

## Build instructions
Requirements: *Minimum JDK 1.6*

Inside project's main directory:
To compile: *./gradlew compileKotlin*
To run tests: *./gradlew test*
To run application: *./gradlew run*

## Console log:
Preparing data...done.

Robbing street (1, 2, 3, 1)...
Robbed money: 4
Performed in 0 ms

Robbing street (100, 20, 100, 20, 100, 1000)...
Robbed money: 1200
Performed in 0 ms

Robbing street (with 1333333 houses)...
Robbed money: 444444222222
Performed in 17 ms

Process finished with exit code 0
