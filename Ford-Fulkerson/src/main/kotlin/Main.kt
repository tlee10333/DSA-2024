package org.example
import org.example.GraphDW
import org.example.GraphTraversal


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {


    var x = GraphDW<String>();

    x.addVertices("a")
    x.addVertices("b")
    x.addVertices("c")
    x.addVertices("d")
    x.addVertices("e")


    x.addEdge("a", "b", 5, 2)
    x.addEdge("a", "c", 3, 4)
    x.addEdge("b", "d", 5)
    x.addEdge("c", "d", 1)
    x.addEdge("d", "e", 2)
    x. addEdge("b", "e", 2)
    println(x.getEdges("a"))
    println(x.getEdgeFlow("a" ,"c"))
    x.setEdgeFlow("a", "c", 2)
    println(x.getEdgeFlow("a" ,"c"))
    println(x.getEdges("a"))

    var s = GraphTraversal(x)
    println(s.bfs("a", "e"))
    println(s.dfs("a", "e"))

}