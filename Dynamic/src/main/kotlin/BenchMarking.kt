package org.example
import org.example.Matrix
import kotlin.system.measureTimeMillis
import kotlin.random.Random


inline fun<T> measureTimeMillisPair(function: () -> T): Pair<T, Long> {
    val startTime = System.currentTimeMillis()
    val result: T = function.invoke()
    val endTime = System.currentTimeMillis()

    return Pair(result, endTime - startTime)
}


fun main(){
    val sizeList = listOf(4,
        1, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192)
        println("Strassen Algo")

        for (n in sizeList){
            var a = Matrix(n)
            var b = Matrix(n)
            for (i in 0 until n){
                a.matrix[i] = Array(n) {Random.nextInt(0, 1000000)}
                b.matrix[i] = Array(n) {Random.nextInt(0, 1000000)}
            }
            val (x, time) =  measureTimeMillisPair{
                a.strassenMultiply(b)
            }
            println(time)

            }
        println("Brute Force")
        for (n in sizeList){
            var a = Matrix(n)
            var b = Matrix(n)
            for (i in 0 until n){
                a.matrix[i] = Array(n) {Random.nextInt(0, 1000000)}
                b.matrix[i] = Array(n) {Random.nextInt(0, 1000000)}
            }
            val (x, time) =  measureTimeMillisPair{
                a.multiplyMatrices(b)
            }
            println(time)

        }





}