package org.example
import org.example.GraphDW


fun <VertexType> fordFulkerson(graph: GraphDW<VertexType>, start: VertexType, end: VertexType): Int{
    val g = GraphTraversal(graph)
    var max_flow =0
    var path = g.dfs(start, end)
    var flow_potential = mutableListOf<Int>()

    while (path!= null){
        for (i in 0 until path.size -1){
            var difference = graph.getEdgeCapacity(path[i], path[i+1])?.minus(graph.getEdgeFlow(path[i], path[i+1])!!)
            if (difference != null) {
                flow_potential.add(difference)
            }
        }
        //Calculating the bottleneck value
        var bottleneck = flow_potential.min()

        //Updating all the flow in the path
        for (i in 0 until path.size -1){
            graph.getEdgeFlow(path[i], path[i+1])?.plus(bottleneck)?.let { graph.setEdgeFlow(path!![i], path!![i+1], it) }
        }
        max_flow+=bottleneck

        //reset everything
        path = g.dfs(start, end)
        flow_potential = mutableListOf<Int>()
        bottleneck =0
    }
    return max_flow
}

fun <VertexType> edmondKarps(graph: GraphDW<VertexType>, start: VertexType, end: VertexType): Int{
    val g = GraphTraversal(graph)
    var max_flow =0
    var path = g.bfs(start, end)
    var flow_potential = mutableListOf<Int>()
    while (path!= null){

        for (i in 0 until path.size -1){
            var difference = graph.getEdgeCapacity(path[i], path[i+1])?.minus(graph.getEdgeFlow(path[i], path[i+1])!!)
            if (difference != null) {
                flow_potential.add(difference)

            }
        }
        //Calculating the bottleneck value
        var bottleneck = flow_potential.min()

        //Updating all the flow in the path
        for (i in 0 until path.size -1){
            graph.getEdgeFlow(path[i], path[i+1])?.plus(bottleneck)?.let { graph.setEdgeFlow(path!![i], path!![i+1], it) }
        }
        max_flow+=bottleneck

        //reset everything
        path = g.dfs(start, end)
        flow_potential = mutableListOf<Int>()
        bottleneck =0
    }
    return max_flow
}