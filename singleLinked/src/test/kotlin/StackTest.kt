import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

import org.example.Stack
class StackTest {
    @Test
    fun getTop() {
        val s: Stack<String> = Stack()
        s.push("hello")
        assertEquals("hello", s.peek())
    }

    @Test
    fun push() {
        val s: Stack<String> = Stack()
        s.push("hello")
        assertFalse(s.isEmpty())
    }

    @Test
    fun pop() {
        val s: Stack<String> = Stack()
        s.push("hello")
        s.pop()
        assertTrue(s.isEmpty())

        s.push("one")
        s.push("two")
        assertEquals("two", s.peek())
    }

    @Test
    fun peek() {
        val s: Stack<String> = Stack()
        s.push("one")
        s.push("two")
        assertEquals("two", s.peek())
    }

    @Test
    fun isEmpty() {
        val s: Stack<String> = Stack()
        assertTrue(s.isEmpty())


    }
}