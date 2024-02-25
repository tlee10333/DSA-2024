import org.example.bubbleSort
import org.example.mergeSort
import org.example.selectionSort
import org.example.quickSort

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MainKtTest {




    @Test
    fun bubbleSort() {
        var solution = listOf(1, 2, 10, 14, 14, 20, 29, 37, 75, 89, 99)
        var unsorted = mutableListOf(37,99,2, 29, 10, 14 , 14, 89, 20, 1, 75)
        assertEquals(solution, bubbleSort(unsorted))
    }

    @Test
    fun mergeSort() {
        var solution = listOf(1, 2, 10, 14, 14, 20, 29, 37, 75, 89, 99)
        var unsorted = mutableListOf(37,99,2, 29, 10, 14 , 14, 89, 20, 1, 75)
        assertEquals(solution, mergeSort(unsorted))

    }

    @Test
    fun selectionSort() {
        var solution = listOf(1, 2, 10, 14, 14, 20, 29, 37, 75, 89, 99)
        var unsorted = mutableListOf(37,99,2, 29, 10, 14 , 14, 89, 20, 1, 75)
        assertEquals(solution, selectionSort(unsorted))

    }

    @Test
    fun quickSort() {
        var solution = listOf(1, 2, 10, 14, 14, 20, 29, 37, 75, 89, 99)
        var unsorted = mutableListOf(37,99,2, 29, 10, 14 , 14, 89, 20, 1, 75)
        assertEquals(solution, quickSort(unsorted))

    }
}