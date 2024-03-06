package org.example
import org.example.Matrix


fun match(str1 : Char, str2: Char): Int{
    if (str1 == str2) {
        return 3
    }
    return -3
}

fun backtracing(m: Matrix,current_i: Int, current_j: Int, str1: List<Char>, str2: List<Char>, seq1: String, seq2: String): Pair<String, String> {


    val diagonal = m.matrix[current_i - 1][current_j - 1]
    val top = m.matrix[current_i - 1][current_j]
    val left = m.matrix[current_i][current_j - 1]


    if (diagonal == 0) {
        return Pair(seq1 + str1[current_i - 1],
            seq2 + str2[current_j - 1])
    }

    if (diagonal != null && top != null && left != null) {
        if (diagonal > left!! && diagonal > top!!) {
            return backtracing(
                m,
                current_i - 1,
                current_j - 1,
                str1,
                str2,
                seq1 + str1[current_i - 1],
                seq2 + str2[current_j - 1]
            )
        } else if (top > left && top > m.matrix[current_i][current_j]!!) {
            return backtracing(m, current_i - 1, current_j, str1, str2, seq1 + str1[current_i - 1], seq2 + "-")
        } else if (left > top && left > m.matrix[current_i][current_j]!!) {
            return backtracing(m, current_i - 1, current_j, str1, str2, seq1 + "-", seq2 + str2[current_j - 1])
        }
        return backtracing(m, current_i - 1, current_j - 1, str1, str2, seq1 + str1[current_i - 1], seq2 + str2[current_j - 1])

    }

    return Pair(seq1, seq2)

}
fun smithWaterman(string1: String, string2: String): Pair<String, String> {

    val list1 = string1.toList() //row
    val list2 = string2.toList() //column

    var large_i = 0
    var large_j = 0

    //Make sure that th
    if (list1.size != list2.size){
        return Pair("", "")

    }


    //Initialize Matrix
    val scoring = Matrix(list1.size +1)
    for (i in 0 until scoring.matrix.size){
        scoring.matrix[0][i] = 0
        scoring.matrix[i][0] = 0
    }

    for (i in 1 until scoring.matrix.size){
        for (j in 1 until scoring.matrix.size){
            val match = scoring.matrix[i-1][j-1]?.plus(match(list1[i-1] , list2[j-1]))
            val delete = scoring.matrix[i-1][j]?.minus(2)
            val insert = scoring.matrix[i][j-1]?.minus(2)

            if (match != null && delete != null && insert != null){
                scoring.matrix[i][j] =  maxOf(match, delete, insert, 0)
            }

            //See if score is larger than anyone else before
            if (scoring.matrix[i][j]!! > scoring.matrix[large_i][large_j]!!){
                large_i = i
                large_j = j
            }


        }
    }
        var seqi = ""
        var seqj = ""

    //Backtrace
    val (a,b) = backtracing(scoring, large_i, large_j, list1, list2, seqi, seqj)
    return Pair(a.reversed(), b.reversed())

}


fun main(){

    val x = "TGTTACGG"
    val y = "GGTTGACT"

    val (p, t) = smithWaterman(y, x)
    if (p != null && t != null) {
        println(p)
        println(t)
    }



}