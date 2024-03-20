package org.example

/**
 * This ``Graph`` represents a directed graph
 * @param VertexType the representation of a vertex in the graph
 *@property vertices a map of vertices with values being another map that holds all edges pointing from the vertice to to other vertices
 *
 */
class GraphDW<VertexType>() {
    var vertices = mutableMapOf<VertexType, MutableMap<VertexType, Pair<Int?, Int?>>>()
    /**
     * Adds a vertice to the graph
     * @param vertice is a new vertice added to the graph
     */
    fun  addVertices(vertice : VertexType){
        vertices.put(vertice, mutableMapOf<VertexType, Pair<Int?, Int?>>())
    }

    /**
     * adds an edge from a vertice to another vertice
     * @param from the vertice that the edge is originating to
     * @param to the vertice that the edge is pointing to
     * @param cost a double value that represents the weight/cost of the edge
     */
    fun addEdge(from: VertexType, to: VertexType, capacity: Int, flow: Int=0) {
        vertices[from]?.put(to, Pair( first = flow,  second =capacity))

        //Residual graph edge
        vertices[to]?.put(from, Pair( first = 0,  second =0))
    }

    /**
     * returns a set of vertices
     */
     fun getVertices(): Set<VertexType> {
        return vertices.keys
    }
    /**
     * Gets all the edges associated with a single vertices
     * @param from is a vertice we want to see all the edges coming from that vertice
     * @return a mutable map of all the edges coming from the inputted vertice
     */
     fun getEdges(from: VertexType): MutableMap<VertexType, Pair<Int?, Int?>>? {
         return vertices[from]
    }
    /**
     * Returns an edge weight given the two nodes it's connected to
     *  @param from the vertice that the edge is originating to
     *  @param to the vertice that the edge is pointing to
     * @return A double value that's the weight of the edge
     */
    fun getEdgeFlow(from: VertexType, to: VertexType): Int? {
        return vertices[from]?.get(to)?.first
    }

    fun getEdgeCapacity(from: VertexType, to: VertexType): Int? {
        return vertices[from]?.get(to)?.second
    }

    fun setEdgeFlow(from: VertexType, to: VertexType, flow: Int) {
            vertices[from]?.set(to, Pair( first = flow,  second = getEdgeCapacity(from, to)))
        //Residual edge
        vertices[to]?.set(from, Pair( first = -flow,  second = 0))

    }

    /**
     * Clears all vertices & edges
     */
     fun clear() {
        vertices = mutableMapOf<VertexType, MutableMap<VertexType, Pair<Int?, Int?>>>()
    }
}


