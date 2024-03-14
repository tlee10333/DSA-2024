package org.example
import java.util.ArrayDeque
import org.example.GraphDW

/**
 * Contains Functions & Classes for Breadth first search and depth first search
 */






/**
 * @param T the type of value to store in the queue
 * @property deque the data structure to implement the Stack
 */
class MyStack<T> {
    private val deque = ArrayDeque<T>()

    /**
     * Add [v] to the top of the stack
     * @param v the element to add
     */
    fun push(v: T) {
        deque.addFirst(v)
    }

    /**
     * Remove element from the top of the stack
     * @return the top element or null if none exists
     */
    fun pop():T? {
        if (deque.isEmpty()) {
            return null
        } else {
            return deque.removeFirst()
        }
    }

    /**
     * @return true if empty, false otherwise
     */
    fun isEmpty():Boolean {
        return deque.isEmpty()
    }

    /**
     * @return the top element in the stack or null if empty
     */
    fun peek():T? {
        return deque.peekFirst()
    }
}


/**
 * @param T the type of value to store in the queue
 * @property deque the data structure to implement the Queue
 */
class MyQueue<T> {
    private val deque = ArrayDeque<T>()

    /**
     * Add [v] to the end of the queue
     * @param v the element to add
     */
    fun enqueue(v: T) {
        deque.addLast(v)
    }

    /**
     * Remove element from the front of the queue
     * @return the first element or null if none exists
     */
    fun dequeue():T? {
        if (deque.isEmpty()) {
            return null
        } else {
            return deque.removeFirst()
        }
    }

    /**
     * @return true if empty, false otherwise
     */
    fun isEmpty():Boolean {
        return deque.isEmpty()
    }

    /**
     * @return the first element in the queue or null if empty
     */
    fun peek():T? {
        return deque.peekFirst()
    }
}


class GraphTraversal<VertexType>(g: GraphDW<VertexType>) {
    private var graph = g

    /**
     * Search through a graph using a breadth-first search
     * @param start the node to start the search
     * @param target the node to search for
     * @return the path from start to target (if one exists) and null otherwise
     */
    fun bfs(start: VertexType, target: VertexType): List<VertexType>? {
        val priorityList = MyQueue<VertexType>()
        val marked: MutableSet<VertexType> = mutableSetOf()
        val parents: MutableMap<VertexType, VertexType> = mutableMapOf()
        marked.add(start)
        priorityList.enqueue(start)
        while (true) {
            // bail out if we don't have anymore values to check
            // Note: using the Elvis operator ensures next is non-nullable
            val next = priorityList.dequeue() ?: break
            if (next == target) {
                return reconstructPath(parents, target)
            }
            graph.getEdges(next)?.forEach { neighbor ->
                if (!marked.contains(neighbor.key)) {
                    parents[neighbor.key] = next
                    marked.add(neighbor.key)
                    priorityList.enqueue(neighbor.key)
                }
            }
        }
        return null
    }

    /**
     * Search through a graph using a depth-first search
     * @param start the node to start the search
     * @param target the node to search for
     * @return the path from start to target (if one exists) and null otherwise
     */
    fun dfs(start: VertexType, target: VertexType):List<VertexType>? {
        val priorityList = MyStack<VertexType>()
        val marked: MutableSet<VertexType> = mutableSetOf()
        // we use parents to reconstruct the path back to [start]
        val parents: MutableMap<VertexType, VertexType> = mutableMapOf()
        marked.add(start)
        priorityList.push(start)
        while (true) {
            // bail out if we don't have anymore values to check
            // Note: using the Elvis operator ensures next is non-nullable
            val next = priorityList.pop() ?: break
            if (next == target) {
                return reconstructPath(parents, target)
            }
            graph.getEdges(next)?.forEach { neighbor ->
                if (!marked.contains(neighbor.key)) {
                    parents[neighbor.key] = next
                    marked.add(neighbor.key)
                    priorityList.push(neighbor.key)
                }
            }
        }
        return null
    }



    /**
     * Backtrack through the path to reconstruct the path
     * from [end] to the path start
     *
     */
    private fun reconstructPath(parents: MutableMap<VertexType, VertexType>,
                                end: VertexType): List<VertexType> {
        val returnValue = mutableListOf(end)
        var curr = end
        while (true) {
            curr = parents[curr] ?: break
            returnValue.add(0, curr)
        }
        return returnValue
    }
}