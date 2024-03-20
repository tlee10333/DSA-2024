package org.example
import org.example.GraphDW
import org.example.GraphTraversal
import org.example.fordFulkerson


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {


    var x = GraphDW<String>();

    x.addVertices("a")
    x.addVertices("b")
    x.addVertices("c")
    x.addVertices("d")
    x.addVertices("e")
    x.addVertices("f")

    x.addEdge("a", "b", 10)
    x.addEdge("b", "c", 25)
    x.addEdge("c", "f", 10)
    x.addEdge("a", "d", 10)
    x.addEdge("d", "e", 15)
    x.addEdge("e", "f", 10)
    x.addEdge("e", "b", 6)



    //Correct Answer is 20

    val n = fordFulkerson(x, "a", "f")
    println(n)

}