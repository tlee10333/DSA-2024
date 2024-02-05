import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*


class doubleListTest {
    @Test
    fun pushFront() {
        val s: doubleList<String> = doubleList()
        s.pushFront( "one")
        assertEquals("one", s.peekFront())

        s.pushFront("two")
        assertEquals("two", s.peekFront())


    }

    @Test
    fun pushBack() {
        //Testing that pushing back once & twice work.
        val s: doubleList<String> = doubleList()
        s.pushBack( "one")
        assertEquals("one", s.peekBack())

        s.pushBack("two")
        assertEquals("two", s.peekBack())

    }

    @Test
    fun popFront() {

        //Testing that popFront works with a pushFront
        val s: doubleList<String> = doubleList()
        s.pushFront("one")
        s.popFront()
        assertTrue(s.isEmpty())

        //Testing that popFront also works with a pushBack
        s.pushBack("one")
        s.popFront()
        assertTrue(s.isEmpty())

        //Test we only pop one at the front
        s.pushFront("one")
        s.pushFront("two")
        s.popFront()
        assertEquals("one", s.peekFront())


        //Make sure popping and pushing account for when there's a single element
        s.pushBack("three")
        s.popFront()
        assertEquals("three", s.peekFront())

    }

    @Test
    fun popBack() {
        val s: doubleList<String> = doubleList()
        s.pushBack("one")
        s.popBack()
        assertTrue(s.isEmpty())

        s.pushBack("one")
        s.popBack()
        assertTrue(s.isEmpty())

        //Test we only pop one at the front
        s.pushFront("one")
        s.pushFront("two")
        s.popBack()
        assertEquals("two", s.peekBack())

        //make list empty again
        s.popFront()

        //Make sure popping and pushing account for when there's a single elememt
        s.pushFront( "one")
        s.pushBack("two")
        s.popBack()
        assertEquals("one", s.peekBack())

    }

    @Test
    fun peekFront() {
        val s: doubleList<String> = doubleList()

        //Testing peekFront with one element
        s.pushBack("one")
        assertEquals("one", s.peekFront())

        //Testing peekFront with pushFront
        s.pushFront("two")
        assertEquals("two", s.peekFront())

    }

    @Test
    fun peekBack() {
        val s: doubleList<String> = doubleList()

        //Testing PeekBack
        s.pushBack("one")
        assertEquals("one", s.peekBack())

        s.pushBack("two")
        assertEquals("two", s.peekBack())


    }

    @Test
    fun isEmpty() {
        val s: doubleList<String> = doubleList()

        s.pushBack("one")
        assertFalse(s.isEmpty())

        s.popBack()
        assertTrue(s.isEmpty())
    }
}

class doubleStackTest {

    @Test
    fun push() {
        val s:  doubleStack<String> = doubleStack()
        s.push("one")

        assertEquals("one", s.peek())

        s.push("two")
        assertEquals("two", s.peek())

    }

    @Test
    fun pop() {

        val s:  doubleStack<String> = doubleStack()
        s.push("one")
        s.pop()

        assertTrue(s.isEmpty())

        s.push("two")
        s.push("three")
        s.pop()
        assertEquals("two", s.peek())



    }

    @Test
    fun peek() {
        val s:  doubleStack<String> = doubleStack()
        s.push("one")
        assertEquals("one", s.peek())

        s.push("two")
        assertEquals("two", s.peek())
        s.pop()
        assertEquals("one", s.peek())
    }

    @Test
    fun isEmpty() {
        val s:  doubleStack<String> = doubleStack()
        assertTrue(s.isEmpty())
        s.push("one")
        assertFalse(s.isEmpty())
        s.pop()
        assertTrue(s.isEmpty())

    }
}

class doubleQueueTest {

    @Test
     fun enqueue() {
        val s:  doubleQueue<String> = doubleQueue()
        s.enqueue("one")

        assertEquals("one", s.peek())

        s.enqueue("two")
        assertEquals("one", s.peek())
    }

    @Test
    fun dequeue() {
        val s:  doubleQueue<String> = doubleQueue()
        s.enqueue("one")
        s.enqueue(("two"))
        s.dequeue()
        assertEquals("two", s.peek())
        s.dequeue()
        assertTrue(s.isEmpty())





    }

    @Test
     fun peek(){
        val s:  doubleQueue<String> = doubleQueue()

        s.enqueue("one")
        assertEquals("one", s.peek())

        s.enqueue("two")
        assertEquals("one", s.peek())
        s.dequeue()
        assertEquals("two", s.peek())


    }
    @Test
     fun isEmpty() {
        val s:  doubleQueue<String> = doubleQueue()
        assertTrue(s.isEmpty())
        s.enqueue("one")
        assertFalse(s.isEmpty())
        s.dequeue()
        assertTrue(s.isEmpty())
    }


}