package org.example
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import org.example.Matrix
import kotlin.system.measureTimeMillis
import kotlin.random.Random
import org.jetbrains.kotlinx.multik.api.*
import org.jetbrains.kotlinx.multik.api.linalg.dot
import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.ndarray.data.*

inline fun<T> measureTimeMillisPair(function: () -> T): Pair<T, Long> {
    val startTime = System.currentTimeMillis()
    val result: T = function.invoke()
    val endTime = System.currentTimeMillis()

    return Pair(result, endTime - startTime)
}


fun main(){
    val sizeList = listOf(
        1, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192)
//    println("Strassen Algorithm - Serial")
//
//    for (n in sizeList){
//        var a = Matrix(n)
//        var b = Matrix(n)
//        for (i in 0 until n){
//            a.matrix[i] = Array(n) {Random.nextInt(0, 1000000)}
//            b.matrix[i] = Array(n) {Random.nextInt(0, 1000000)}
//        }
//        val (x, time) =  measureTimeMillisPair{
//            a.strassenMultiply(b)
//        }
//        println(time)
//
//    }
//    println("Brute Force - Serial")
//    for (n in sizeList){
//        var a = Matrix(n)
//        var b = Matrix(n)
//        for (i in 0 until n){
//            a.matrix[i] = Array(n) {Random.nextInt(0, 1000000)}
//            b.matrix[i] = Array(n) {Random.nextInt(0, 1000000)}
//        }
//        val (x, time) =  measureTimeMillisPair{
//            a.multiplyMatrices(b)
//        }
//        println(time)
//
//    }

    println("Strassen Algorithm - Parallel")

    for (n in sizeList){
        var a = Matrix(n)
        var b = Matrix(n)
        for (i in 0 until n){
            a.matrix[i] = Array(n) {Random.nextInt(0, 1000000)}
            b.matrix[i] = Array(n) {Random.nextInt(0, 1000000)}
        }
        val (x, time) =  measureTimeMillisPair{
            runBlocking {
                a.strassenMultiplyParallel(b)
            }
        }
        println(time)
    }

    println("Multik Library")

    for (n in sizeList){
        val a =  mk.zeros<Int, D2>(intArrayOf(n, n), DataType.IntDataType)
        var b =  mk.zeros<Int, D2>(intArrayOf(n, n), DataType.IntDataType)
        for (i in 0 until n){
            a[i] = mk.ndarray(List(n) {Random.nextInt(0, 1000000)})
            b[i] = mk.ndarray(List(n) {Random.nextInt(0, 1000000)})
        }

        val (x, time) =  measureTimeMillisPair{
            runBlocking {
                a.dot(b)
            }
        }
        println(time)
    }





    //RESULTS
//
//    Strassen Algo
//    0
//    0
//    1
//    3
//    10
//    30
//    168
//    693
//    4569
//    11920
//    85355
//    607279
//    4341998

//    Brute Force
//    0
//    0
//    0
//    0
//    0
//    1
//    9
//    225
//    787
//    11556
//    165898
//    1618215
//    15040321

//    Strassen Algorithm - Parallel
//    23
//    9
//    2
//    3
//    10
//    27
//    194
//    702
//    4410
//    29328
//    14635
//    95947
//    673549
//    Multik Library
//    207
//    0
//    0
//    1
//    1
//    4
//    3
//    12
//    73
//    565
//    4502
//    37120
//    303810
//


}

