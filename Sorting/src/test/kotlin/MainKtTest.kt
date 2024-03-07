import org.example.bubbleSort
import org.example.mergeSort
import org.example.selectionSort
import org.example.quickSort

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MainKtTest {



    //Tests BubbleSort sorts correctly. This test list has repeating numbers and worst case unsorted placement
    @Test
    fun bubbleSort() {
        var solution = listOf(1, 2, 10, 14, 14, 20, 29, 37, 75, 89, 99)
        var unsorted = mutableListOf(37,99,2, 29, 10, 14 , 14, 89, 20, 1, 75)
        assertEquals(solution, bubbleSort(unsorted))
    }

    //Tests MergeSort sorts correctly. This test list has repeating numbers and worst case unsorted placement
    @Test
    fun mergeSort() {
        var solution = listOf(1, 2, 10, 14, 14, 20, 29, 37, 75, 89, 99)
        var unsorted = mutableListOf(37,99,2, 29, 10, 14 , 14, 89, 20, 1, 75)
        assertEquals(solution, mergeSort(unsorted))

    }
    //Tests selectionSort sorts correctly. This test list has repeating numbers and worst case unsorted placement
    @Test
    fun selectionSort() {
        var solution = listOf(1, 2, 10, 14, 14, 20, 29, 37, 75, 89, 99)
        var unsorted = mutableListOf(37,99,2, 29, 10, 14 , 14, 89, 20, 1, 75)
        assertEquals(solution, selectionSort(unsorted))

    }
    //Tests quickSort sorts correctly. This test list has repeating numbers and worst case unsorted placement
    @Test
    fun quickSort() {
        var solution = listOf(1, 2, 10, 14, 14, 20, 29, 37, 75, 89, 99)
        var unsorted = mutableListOf(37,99,2, 29, 10, 14 , 14, 89, 20, 1, 75)
        assertEquals(solution, quickSort(unsorted))

    }
}