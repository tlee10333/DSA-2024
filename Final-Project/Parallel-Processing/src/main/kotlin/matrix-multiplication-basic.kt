package org.example
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

/**
 * matrix class that will
 */
class Matrix(n: Int) {

    internal var size = n

    //Public variables
    var matrix: Array<Array<Int?>> =  Array(size) { kotlin.Array<kotlin.Int?>(size) { null } }

    fun add(other: Matrix): Matrix {
        val tempMatrix = Matrix(matrix.size)
        for (i in 0 until tempMatrix.size) {
            for (j in 0 until tempMatrix.size) {
                tempMatrix.matrix[i][j] = matrix[i][j]?.plus(other.matrix[i][j]!!)
            }
        }

        return tempMatrix
    }

    fun subtract(other: Matrix): Matrix {
        val tempMatrix = Matrix(matrix.size)
        for (i in 0 until tempMatrix.size) {
            for (j in 0 until tempMatrix.size) {
                tempMatrix.matrix[i][j] = matrix[i][j]?.minus(other.matrix[i][j]!!)
            }
        }
        return tempMatrix
    }



    fun multiplyMatrices(other: Matrix): Matrix? {

        //We're doing current Matrix times other Matrix
        val matrix2 = other.matrix

        val row1 = matrix.size
        val col1 = matrix[0].size
        val row2 = matrix2.size
        val col2 = matrix2[0].size

        if (col1 != row2){
            return null
        }
        val product = Matrix(size)
        for (i in 0 until row1) {
            for (j in 0 until col2) {
                for (k in 0 until col1) {
                    product.matrix[i][j] = matrix[i][k]?.times(matrix2[k][j]!!)?.let { product.matrix[i][j]?.plus(it) }
                }
            }
        }
        return product
    }



    fun strassenMultiply(other: Matrix): Matrix? {
        val matrix2 = other.matrix
        //If size doesn't work
        if (matrix.size != other.matrix.size){
            return null
        }

        //If threshold smaller than 1024, than just do brute force since that's faster
        if (matrix.size == 1024){
            return multiplyMatrices(other)
        }

        //Base Case 1x1 works
        if (matrix.size == 1 && other.matrix.size == 1){
            val tempMatrix = Matrix(1)
            tempMatrix.matrix[0][0]= matrix[0][0]?.times(other.matrix[0][0]!!)
            return tempMatrix

        }

        //Seperating into the 4 quadrants
        val a = Matrix(size/2)
        for (i in 0..size/2 -1){
            a.matrix[i] = matrix[i].slice(0..size/2 -1).toTypedArray()
        }

        val b = Matrix(size/2)
        for (i in 0..size/2 -1){
            b.matrix[i] = matrix[i].slice(size/2..size -1).toTypedArray()
        }

        val c = Matrix(size/2)
        for (i in size/2..size -1){
            c.matrix[i - size/2] = matrix[i].slice(0..size/2 -1).toTypedArray()
        }

        val d = Matrix(size/2)
        for (i in size/2..size -1){
            d.matrix[i - size/2] = matrix[i].slice(size/2..size -1).toTypedArray()
        }


        val e = Matrix(size/2)
        for (i in 0..size/2 -1){
            e.matrix[i] = matrix2[i].slice(0..size/2 -1).toTypedArray()
        }

        val f = Matrix(size/2)
        for (i in 0..size/2 -1){
            f.matrix[i] = matrix2[i].slice(size/2..size -1).toTypedArray()
        }

        val g = Matrix(size/2)
        for (i in size/2..size -1){
            g.matrix[i- size/2] = matrix2[i].slice(0..size/2 -1).toTypedArray()
        }

        val h = Matrix(size/2)
        for (i in size/2..size -1){
            h.matrix[i - size/2] = matrix2[i].slice(size/2..size -1).toTypedArray()
        }

        //Doing the 7 calculations
        val tempMatrix = Matrix(size)
        val m1 = (a.add(d))?.strassenMultiply(e.add(h))
        val m2 = (c.add(d))?.strassenMultiply(e)
        val m3 = a.strassenMultiply(f.subtract(h))
        val m4 = d.strassenMultiply(g.subtract(e))
        val m5 = (a.add(b)).strassenMultiply(h)
        val m6 = (c.subtract(a)).strassenMultiply(e.add(f))
        val m7 = (b.subtract(d)).strassenMultiply(g.add(h))

        //Adding them together
        val c11 = m7?.let { (m5?.let { (m4?.let { m1?.add(it) })?.subtract(it) })?.add(it) }
        val c12 = m5?.let { m3?.add(it) }
        val c21 = m4?.let { m2?.add(it) }
        val c22 = m6?.let { (m3?.let { (m2?.let { m1?.subtract(it) })?.add(it) })?.add(it) }



        //combining everything together
        for (i in 0 until size/2){
            if (c11 != null) {
                for (j in 0 until size/2)
                    tempMatrix.matrix[i][j] = c11.matrix[i][j]
            }
        }

        for (i in 0 until size/2){
            if (c12 != null) {
                for (j in size/2 until size)
                    tempMatrix.matrix[i][j] = c12.matrix[i][j - size/2]
            }
        }

        for (i in size/2 until size){
            if (c21 != null) {
                for (j in 0 until size/2)
                    tempMatrix.matrix[i][j] = c21.matrix[i- size/2][j]
            }
        }

        for (i in size/2 until size){
            if (c22 != null) {
                for (j in size/2 until size)
                    tempMatrix.matrix[i][j] = c22.matrix[i- size/2][j - size/2]
            }
        }

        return tempMatrix

    }


    suspend fun strassenMultiplyParallel(other: Matrix): Matrix? = runBlocking {
        val matrix2 = other.matrix
        //If size doesn't work
        if (matrix.size != other.matrix.size){
            return@runBlocking null
        }

//        //If threshold smaller than 1024, than just do brute force since that's faster
//        if (matrix.size == 1024){
//            return@runBlocking multiplyMatrices(other)
//        }

        //Base Case 1x1 works
        if (matrix.size == 1 && other.matrix.size == 1){
            val tempMatrix = Matrix(1)
            tempMatrix.matrix[0][0]= matrix[0][0]?.times(other.matrix[0][0]!!)
            return@runBlocking tempMatrix

        }

        //Seperating into the 4 quadrants
        val a = Matrix(size/2)
        for (i in 0..size/2 -1){
            a.matrix[i] = matrix[i].slice(0..size/2 -1).toTypedArray()
        }

        val b = Matrix(size/2)
        for (i in 0..size/2 -1){
            b.matrix[i] = matrix[i].slice(size/2..size -1).toTypedArray()
        }

        val c = Matrix(size/2)
        for (i in size/2..size -1){
            c.matrix[i - size/2] = matrix[i].slice(0..size/2 -1).toTypedArray()
        }

        val d = Matrix(size/2)
        for (i in size/2..size -1){
            d.matrix[i - size/2] = matrix[i].slice(size/2..size -1).toTypedArray()
        }


        val e = Matrix(size/2)
        for (i in 0..size/2 -1){
            e.matrix[i] = matrix2[i].slice(0..size/2 -1).toTypedArray()
        }

        val f = Matrix(size/2)
        for (i in 0..size/2 -1){
            f.matrix[i] = matrix2[i].slice(size/2..size -1).toTypedArray()
        }

        val g = Matrix(size/2)
        for (i in size/2..size -1){
            g.matrix[i- size/2] = matrix2[i].slice(0..size/2 -1).toTypedArray()
        }

        val h = Matrix(size/2)
        for (i in size/2..size -1){
            h.matrix[i - size/2] = matrix2[i].slice(size/2..size -1).toTypedArray()
        }

        //Doing the 7 calculations
        val tempMatrix = Matrix(size)
        val m1 = async { a.add(d)?.strassenMultiply(e.add(h)) }
        val m2 = async { c.add(d)?.strassenMultiply(e) }
        val m3 = async { a.strassenMultiply(f.subtract(h)) }
        val m4 = async { d.strassenMultiply(g.subtract(e)) }
        val m5 = async { a.add(b).strassenMultiply(h) }
        val m6 = async { c.subtract(a).strassenMultiply(e.add(f)) }
        val m7 = async { b.subtract(d).strassenMultiply(g.add(h)) }


        //Adding them together
        val c11 = m1.await()!!.add( m4.await()!!).subtract( m5.await()!!).add( m7.await()!!)
        val c12 = m3.await()!!.add(m5.await()!!)
        val c21 = m2.await()!!.add( m4.await()!!)
        val c22 = m1.await()!!.subtract(m2.await()!!).add( m3.await()!!).add( m6.await()!!)

        for (i in 0 until size/2){
            if (c11 != null) {
                for (j in 0 until size/2)
                    tempMatrix.matrix[i][j] = c11.matrix[i][j]
            }
        }

        for (i in 0 until size/2){
            if (c12 != null) {
                for (j in size/2 until size)
                    tempMatrix.matrix[i][j] = c12.matrix[i][j - size/2]
            }
        }

        for (i in size/2 until size){
            if (c21 != null) {
                for (j in 0 until size/2)
                    tempMatrix.matrix[i][j] = c21.matrix[i- size/2][j]
            }
        }

        for (i in size/2 until size){
            if (c22 != null) {
                for (j in size/2 until size)
                    tempMatrix.matrix[i][j] = c22.matrix[i- size/2][j - size/2]
            }
        }
        return@runBlocking tempMatrix
    }

}

fun main() {
    var x = Matrix(4)
    var y = Matrix (4)

    x.matrix[0]= arrayOf(1,2 , 3, 4)
    x.matrix[1]= arrayOf(4,5, 6, 7)
    x.matrix[2]= arrayOf(1,2 , 3, 4)
    x.matrix[3]= arrayOf(4,5, 6, 7)

    y.matrix[0]= arrayOf(10,11, 12, 13)
    y.matrix[1]= arrayOf(20,21, 22, 23)
    y.matrix[2]= arrayOf(10,11, 12, 13)
    y.matrix[3]= arrayOf(20,21, 22, 23)

    runBlocking {
        var c = x.strassenMultiply(y)
        if (c != null) {
            println(c.matrix.contentDeepToString())
        }
    }



//
//    //var s = x.strassenMultiply(y)
//    if (s != null) {
//        println(s.matrix.contentDeepToString())
//    }



//
//    if (d != null) {
//        println(d.matrix.contentDeepToString())
//    }












}