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
    }

    /**
     * Clears all vertices & edges
     */
     fun clear() {
        vertices = mutableMapOf<VertexType, MutableMap<VertexType, Pair<Int?, Int?>>>()
    }
}

/**
 * Creates a Priority Queue Object
 */
class PriorityQueue<T>  {
    val queue = MinHeap<T>()
    /**
     * @return true if the queue is empty, false otherwise
     */
     fun isEmpty(): Boolean {
        return queue.isEmpty()
    }
    /**
     * Add [elem] with at level [priority]
     */
     fun addWithPriority(elem: T, priority: Double) {
        queue.insert(elem, priority)
    }
    /**
     * Get the next (lowest priority) element.
     * @return the next element in terms of priority.  If empty, return null.
     */
     fun next(): T? {
        return queue.getMin()
    }
    /**
     * Adjust the priority of the given element
     * @param elem whose priority should change
     * @param newPriority the priority to use for the element
     *   the lower the priority the earlier the element int
     *   the order.
     */
     fun adjustPriority(elem: T, newPriority: Double) {
        queue.adjustHeapNumber(elem, newPriority)
    }
}


/**x.addEdge("a", "b", 5)
 * Function that implements dijkstra algorithm for shortest path-finding
 *
 * @param graph is a directed & weighted graph that dijkstra will traverse through
 * @param start is the start of the graph
 * @param goal is the end goal location
 * @return pair of prev which shows which nodes lead to goal the fastest, and dist which is a mutable map that shows the total shortest cost of reaching goal
 */
fun <T> dijkstra(graph: GraphDW<T>, start: T, goal : T) : Pair<MutableMap<T,T?>, MutableMap<T,Double?>>{
    var priorityList = PriorityQueue<T>() //Keeps track of whom to visit
    //Bookkeeping
    var prev  = mutableMapOf<T, T?>() //Will keep track of who's where (will find the shortest path)
    var dist = mutableMapOf<T,Double?>() //Keeps track of the discovered weights/cost of each edge
    for (node in graph.getVertices()){
        prev.put(node, null)
        dist.put(node, null)
    }
    //Starting point
    var currentNode = start
    var nextNodes = graph.getEdges(currentNode)
    var edgeWeight = 0.0
    dist[start] = 0.0 //This doesn't have any
    prev[start] = null

    while (1==1) {
        if (nextNodes != null) {
            nextNodes.forEach { entry ->
                edgeWeight = graph.getEdgeCapacity(currentNode, entry.key)!! + dist[currentNode]!! //Total edge weight from currentNode
                //All all nextNodes to the priority list
                priorityList.addWithPriority(entry.key, edgeWeight)

                //Only update distance or previous if it's null or our new node's edgeweight is smaller.
                if (dist[entry.key] == null || dist[entry.key]!! > edgeWeight) {
                    dist[entry.key] = edgeWeight
                    prev[entry.key] = currentNode
                }
            }
            //IF priorityList is empty
            if (priorityList.isEmpty()){
                break
            }
            //visit first node in priority list
            currentNode = priorityList.next()!!
            nextNodes = graph.getEdges(currentNode)
        }
    }
    return prev to dist
}
