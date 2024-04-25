import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.example.RedBlack

class RedBlackTest {

    @Test
    fun leftRotate() {
        val tree = RedBlack<Int>()

        // Create a tree with specific nodes to test left rotation
        tree.insert(4)
        tree.insert(2)
        tree.insert(6)
        tree.insert(1)
        tree.insert(3)
        tree.insert(5)
        tree.insert(7)

        // Perform a left rotation
        tree.leftRotate(tree.lookup(tree.root, 4)!!)

        // Assert the new structure after left rotation
        assertEquals(6, tree.lookup(tree.root, 4)?.key) // Parent node after rotation
        assertEquals(7, tree.lookup(tree.root, 6)?.key) // Right child of the new parent
        assertEquals(4, tree.lookup(tree.root, 5)?.key) // Left child of the new parent
        assertEquals(5, tree.lookup(tree.root, 2)?.key) // Right child of the original left child
        assertEquals(2, tree.lookup(tree.root, 1)?.key) // Left child of the original left child
        assertEquals(3, tree.lookup(tree.root, 3)?.key) // Right child of the original left child
    }


    @Test
    fun rightRotate() {
        val tree = RedBlack<Int>()

        // Create a tree with specific nodes to test right rotation
        tree.insert(4)
        tree.insert(2)
        tree.insert(6)
        tree.insert(1)
        tree.insert(3)
        tree.insert(5)
        tree.insert(7)

        // Perform a right rotation
        tree.rightRotate(tree.lookup(tree.root, 2)!!)

        // Assert the new structure after right rotation
        assertEquals(3, tree.lookup(tree.root, 2)?.key) // Parent node after rotation
        assertEquals(4, tree.lookup(tree.root, 1)?.key) // Left child of the new parent
        assertEquals(2, tree.lookup(tree.root, 3)?.key) // Right child of the new parent
        assertEquals(5, tree.lookup(tree.root, 4)?.key) // Right child of the original right child
        assertEquals(6, tree.lookup(tree.root, 5)?.key) // Left child of the original right child
        assertEquals(7, tree.lookup(tree.root, 6)?.key) // Right child of the original right child
    }


    @Test
    fun insertMaintain() {
        val tree = RedBlack<Int>()

        // Scenario 1: Testing if a single red node insertion is maintained
        tree.insert(1)
        assertEquals("R", tree.root?.color)

        // Scenario 2: Testing if the Red-Black Tree is maintained after inserting multiple nodes
        tree.insert(2)
        assertEquals("R", tree.root?.color)
        assertEquals("R", tree.lookup(tree.root, 2)?.color)

        tree.insert(3)
        assertEquals("R", tree.root?.color)
        assertEquals("R", tree.lookup(tree.root, 3)?.color)

    }

    @Test
    fun insertAndLookup() {
        val tree = RedBlack<Int>()

        // Insert some keys into the tree
        tree.insert(10)
        tree.insert(5)
        tree.insert(15)
        tree.insert(12)
        tree.insert(18)
        // Assert that each inserted key can be looked up in the tree
        assertEquals(10, tree.lookup(tree.root, 10)?.key)
        assertEquals(5, tree.lookup(tree.root, 5)?.key)
        assertEquals(15, tree.lookup(tree.root, 15)?.key)
        assertEquals(12, tree.lookup(tree.root, 12)?.key)
        assertEquals(18, tree.lookup(tree.root, 18)?.key)

        // Assert that a key not present returns null
        assertNull(tree.lookup(tree.root, 100)?.key)

    }
}