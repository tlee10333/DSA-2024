package org.example

import kotlin.random.Random
import kotlin.system.measureTimeMillis


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



inline fun<T> measureTimeMillisPair(function: () -> T): Pair<T, Long> {
    val startTime = System.currentTimeMillis()
    val result: T = function.invoke()
    val endTime = System.currentTimeMillis()

    return Pair(result, endTime - startTime)
}


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
//fun main() {
//    var unsorted = mutableListOf(37,99,2, 29, 10, 14 , 14, 89, 20, 1, 75,12,3,45,6,3,24567876,23454,12344,1,456,8,4,24,345,13)
//     unsorted = mutableListOf(37,99,2, 29, 10, 14 , 14, 89, 20, 1, 75)
//
//
//
////    println(bubbleSort(unsorted))
////    println(mergeSort(unsorted))
////    println(selectionSort(unsorted))
////    println(quickSort(unsorted))
////
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
//
//        val (x, time) =  measureTimeMillisPair{
//
//            quickSort(randomValues.toMutableList())
//        }
//        println(time)
//
//
//    }
//
//
//}
//
