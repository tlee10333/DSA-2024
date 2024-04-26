import org.jetbrains.kotlinx.multik.api.d2array
import org.jetbrains.kotlinx.multik.api.ndarray
import kotlin.random.Random
import org.jetbrains.kotlinx.multik.api.*
import org.jetbrains.kotlinx.multik.api.linalg.dot
import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.ndarray.data.*
fun main() {
    var amplititudes  = IntArray(10) { Random.nextInt() }.asList()
    var x =  mk.d2array(2,2) {it * it}
    var y = mk.d2array(2,2) {it * it}
    println(x)
    var n =4
    val matrix = mk.zeros<Int, D2>(intArrayOf(n, n), DataType.IntDataType)
    val matrix2 = mk.zeros<Int, D2>(intArrayOf(n, n), DataType.IntDataType)
    println(matrix[1])
    var s = List(n) {Random.nextInt(0, 1000000)}

    matrix[1] = mk.ndarray(s)
    println(matrix[1])


















}