package org.example
import org.example.GraphDW


/**
 * A function that implements a Ford Fulkerson Algorithm given a sink and source and a graph
 *
 * @param graph is a GraphDW object that contains the graph data
 * @param start is the source node in our graph
 * @param end is the sink node in our graph
 * @return Int value which is the max flow of a graph
 */
fun <VertexType> fordFulkerson(graph: GraphDW<VertexType>, start: VertexType, end: VertexType): Int{
    val g = GraphTraversal(graph)
    var max_flow =0
    //Ford Fulkerson doesn't specify a specific path finding algorithm so I chose DFS
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

/**
 * A function that implements the Edmond Karps Variant Algorithm given a sink and source and a graph. The only difference is that EK specifically uses BFS
 *
 * @param graph is a GraphDW object that contains the graph data
 * @param start is the source node in our graph
 * @param end is the sink node in our graph
 * @return Int value which is the max flow of a graph
 */
 */
fun <VertexType> edmondKarps(graph: GraphDW<VertexType>, start: VertexType, end: VertexType): Int{
    val g = GraphTraversal(graph)
    var max_flow =0
    //HAS to use BFS
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