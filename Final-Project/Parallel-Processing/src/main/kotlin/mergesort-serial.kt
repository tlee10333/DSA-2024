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
