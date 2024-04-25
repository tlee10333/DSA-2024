package org.example

//Variables
class Node<T>(
    var key: T,
    var parent: Node<T>? = null,
    var left: Node<T>? = null,
    var right: Node<T>? = null,
    var color: String = "R"
)


/**
 * A Red-Black Tree implementation.
 *
 * @param T The type of elements stored in the tree. It needs to be a comparable type so that we can use < on it
 */
class RedBlack<T : Comparable<T>>{
    var root: Node<T>? = null


    /**
     * Performs a left rotation on the given node.
     *
     * @param x The node to perform the left rotation on.
     * @return false if didn't rotate, else return true
     */
     fun leftRotate(x: Node<T>): Boolean {


        val x_right = x.right

        //If the right node of x is false, then we fail to rotate
        if (x_right == null){
            return false
        }
        //rotate
        x.right = x_right.left
        x_right.left?.parent = x
        x_right.parent = x.parent
        if (x.parent == null) {
            root = x_right
        } else if (x == x.parent!!.left) {
            x.parent!!.left = x_right
        } else {
            x.parent!!.right = x_right
        }
        x_right.left = x
        x.parent = x_right
        return true
    }



    /**
     * Performs a right rotation on the given node.
     *
     * @param x The node to perform the right rotation on.
     * @return false if didn't rotate, else return true
     *
     */
    fun rightRotate(x: Node<T>): Boolean {
        val x_left = x.left

        //If the right node of x is false, then we fail to rotate
        if (x_left == null){
            return false
        }

        //rotate
        x.left = x_left.right
        x_left.right?.parent = x
        x_left.parent = x.parent
        if (x.parent == null) {
            root = x_left
        } else if (x == x.parent!!.left) {
            x.parent!!.left = x_left
        } else {
            x.parent!!.right = x_left
        }
        x_left.right = x
        x.parent = x_left
        return true
    }


    /**
     * Fixes the Red-Black Tree properties after an insertion.
     *
     * @param z The newly inserted node.
     */
    fun insertMaintain(z: Node<T>) {
        var x = z
        while (x.parent?.color == "R") {
            val parent = x.parent ?: break
            val grandparent = parent.parent ?: break

            if (parent == grandparent.left) {
                val uncle = grandparent.right
                if (uncle?.color == "R") {
                    // Case 1: Uncle is RED
                    parent.color = "B"
                    uncle.color = "B"
                    grandparent.color = "R"
                    x = grandparent
                } else {
                    // Case 2: Uncle is BLACK
                    if (x == parent.right) {
                        // Case 2a: x is a right child
                        x = parent
                        leftRotate(x)
                    }
                    // Case 2b: x is a left child or after rotation
                    parent.color = "B"
                    grandparent.color = "R"
                    rightRotate(grandparent)
                }
            } else {
                // Mirror of the above cases for when parent is a right child
                val uncle = grandparent.left
                if (uncle?.color == "R") {
                    // Case 1: Uncle is RED
                    parent.color = "B"
                    uncle.color = "B"
                    grandparent.color = "R"
                    x = grandparent
                } else {
                    // Case 2: Uncle is BLACK
                    if (x == parent.left) {
                        // Case 2a: x is a left child
                        x = parent
                        rightRotate(x)
                    }
                    // Case 2b: x is a right child or after rotation
                    parent.color = "B"
                    grandparent.color = "R"
                    leftRotate(grandparent)
                }
            }
        }
        root?.color = "R"
    }


    /**
     * Insert function handles inserting a new node
     */
    fun insert(key: T) {
        val newNode = Node(key)
        var y: Node<T>? = null
        var x = root
        while (x != null) {
            y = x
            if (newNode.key < x.key) {
                x = x.left
            } else {
                x = x.right
            }
        }
        newNode.parent = y
        if (y == null) {
            root = newNode
        } else if (newNode.key < y.key) {
            y.left = newNode
        } else {
            y.right = newNode
        }
        newNode.color = "R"
        insertMaintain(newNode)
    }


    /**
     *
     * Lookup function that searches for the given key written recursively
     *
     * @param node is a node in our tree. When we initially run this function, we should have the first node be the root node
     * @param key is the actual key value we care about/are searching for.
     *
     * @return a node that has the key we're looking for, or null if we don't find it.
     */

    fun lookup(node: Node<T>?, key: T): Node<T>? {
        //Base Case: We find our node or no more nodes exist (we hit a leaf)
        if (node == null || key == node.key) {
            return node
        }
        //Look at left & right nodes
        if (key < node.key) {
            return lookup(node.left, key)
        } else {
            return lookup(node.right, key)
        }
    }

}

fun main() {
    val tree = RedBlack<Int>()
    tree.insert(7)
    tree.insert(3)
    tree.insert(18)
    tree.insert(10)
    tree.insert(22)
    tree.insert(8)
    tree.insert(11)
    tree.insert(26)
    tree.insert(2)
    tree.insert(6)

}
