package org.example

import kotlin.random.Random
import kotlin.system.measureTimeMillis


/**
 * Implements bubble sort algorithm
 *
 * How it works:
 *  Loops through the list from 0 to end, compare i & i+1, and switch them if i > i+1
 *  Once we loop through the list once and do not switch any values, we can stop. Very brute forcing.
 *  Best: O(n), Worst O(n^2)
 *  @param unsortedList is an unsorted list of numbers
 *  @return A sorted version of the unsortedList input
 */
fun bubbleSort(unsortedList: MutableList<Int> ): MutableList<Int> {
    var unsorted = unsortedList.toMutableList()
    var inOrder = false
    var tempVal =0

    while (!inOrder) {
        inOrder = true
        for (i in 0..(unsorted.size - 2)) {
            if (unsorted[i] > unsorted[i+1]){
                inOrder = false
                tempVal = unsorted[i]
                unsorted[i] = unsorted[i+1]
                unsorted[i+1] = tempVal
            }
        }
    }
return unsorted
}


/**
 * Implements mergeSort algorithm (RECURSIVE)
 *
 * How it works:
 *  Split list into half recursively, then sort the 2 elements.
 *  As we put back 2 halves together, we basically start from the first index and then compare if the left list or the right list has a larger one.
 *  Add the smaller element to the final list, increment/remove it from it's smaller list, and compare again.
 *  Best: n logn, Worst n logn
 *
 *  @param unsorted is an unsorted list of numbers
 *  @return A sorted version of the unsortedList input
 */
fun mergeSort(unsorted: List<Int>) : List<Int> {
    //Base Cases
    if (unsorted.size <= 1){
        return unsorted
    }

        //Two sorted halves
        var L = mergeSort(unsorted.subList(0, unsorted.size / 2))
        var R = mergeSort(unsorted.subList(unsorted.size / 2, unsorted.size))  //If ever odd R will be the odd length one

        var i = 0
        var j = 0
        val tempList = mutableListOf<Int>()

        while (i < L.size && j < R.size) {
            if (L[i] < R[j]) {
                tempList.add(L[i])
                i++
            } else {
                tempList.add(R[j])
                j++
            }
        }
        while (i < L.size) {
            tempList.add(L[i])
            i++
        }

        while (j < R.size) {
            tempList.add(R[j])
            j++
        }

    return tempList.toList()

}

/**
 * Imelements selectionSort Algorithm
 *
 * How it works:
 *  Loop thorugh the list, find the smallest value per loop, and put it in the front of the list. Do it again & again until you're done
 *
 *  Best: O(n^2), Worst: O(n^2)
 *
 *  @param unsortedList is the unsorted list we're given
 *  @return a list which is the sorted version of unsortedList
 */
fun selectionSort(unsortedList: MutableList<Int>): List<Int> {
    var unsorted = unsortedList.toMutableList()


    for (i in 0.. (unsorted.size-1)){
        var max = unsorted[0]
        var maxIndex  =0
        var temp = 0
        for (j in 0..(unsorted.size - i -1)){
            if (unsorted[j]> max){
                max = unsorted[j]
                maxIndex = j
            }
        }

    temp = unsorted[unsorted.size - i -1]
    unsorted[unsorted.size - i - 1] = max
        unsorted[maxIndex] = temp
    }


    return unsorted
}

/**
 * Implements quickSort algorithm (RECURSIVE)
 *
 * How it works:
 *  Choose pivot is the first element in the list
 *  Go through the list and split it so that the left list is all values smaller than pivot, and right is all values larger than pivot
 *  Put the pivot in the middle of these two lists, and then recursively do this again for the 2 halved lists on the left and right again (so choose another pivot and do it again)
 *  Just put everything back together, no need to sort when merging.
 * Best: O(nlogn), Worst: O(n^2)
 *  @param unsortedList is an unsorted list of numbers
 *  @return A sorted version of the unsortedList input
 */
fun quickSort(unsortedList: MutableList<Int>): List<Int>{

    //Base case
    if (unsortedList.size <= 1){
        return unsortedList
    }

    var unsorted = unsortedList.toMutableList()
    var pivot = unsorted[0]
    var leftIndex =1
    var temp =0

    //Sorting
    for (i in 1..unsorted.size -1){
        if (unsorted[i] < pivot) {
            temp = unsorted[leftIndex]
            unsorted[leftIndex] = unsorted[i]
            unsorted[i] = temp
            temp =0
            leftIndex++
        }
    }
    temp = unsorted[leftIndex -1]
    unsorted[leftIndex -1] = pivot
    unsorted[0] = temp
    var L = quickSort(unsorted.subList(0,leftIndex-1))
    var R = quickSort(unsorted.subList(leftIndex,unsorted.size))
    return L+ unsorted[leftIndex -1] + R
}


//BENCHMARKING CODE
/**
 * Function to calculate the benchmark time for running code
 *
 * @param function is a function we're running
 * @return is a pair, the first is whatever the function outputs as a result, and the second is the time it took to run everything
 */
inline fun<T> measureTimeMillisPair(function: () -> T): Pair<T, Long> {
    val startTime = System.currentTimeMillis()
    val result: T = function.invoke()
    val endTime = System.currentTimeMillis()

    return Pair(result, endTime - startTime)
}

//fun main() {

//    val timeList = listOf(10,
//        100,
//        200,
//        300,
//        400,
//        500,
//        600,
//        700,
//        800,
//        900,
//        1000,
//        5000,
//        8000,
//        10000,
//        30000,
//        50000,
//        70000,
//        100000)
//
//
//    for (time in timeList){
//        var randomValues = List(time) { Random.nextInt(0, 1000000) }
//        val (x, time) =  measureTimeMillisPair{
//           quickSort(randomValues.toMutableList())
//        }
//        println(time)
//        var randomValues = List(time) { Random.nextInt(0, 1000000) }
//        val (x, time) =  measureTimeMillisPair{
//           mergeSort(randomValues.toMutableList())
//        }
//        println(time)
//
//        var randomValues = List(time) { Random.nextInt(0, 1000000) }
//        val (x, time) =  measureTimeMillisPair{
//           BubbleSort(randomValues.toMutableList())
//        }
//        println(time)
//        var randomValues = List(time) { Random.nextInt(0, 1000000) }
//        val (x, time) =  measureTimeMillisPair{
//           selectionSort(randomValues.toMutableList())
//        }
//        println(time)
//
//
//    }
//
//
//}
//
