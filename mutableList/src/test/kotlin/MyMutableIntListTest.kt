import org.example.MyMutableIntList
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MyMutableListTest {

    @Test
    fun add() {
        val l = MyMutableIntList()
        l.add(20)
        for (i in 0..<10) {
            l.add(i)
        }
        assertEquals(l.size(), 11)
        assertEquals(l[10], 9)
    }
    @Test
    fun clear() {
        val l = MyMutableIntList()
        l.add(33)
        for (i in 0..<10) {
            l.add(i)
        }
        l.clear()
        assertEquals(l.size(), 0)
    }

    @Test
    fun size() {
        val l = MyMutableIntList()
        l.add(99)
        for (i in 0..<10) {
            l.add(i)
        }
        assertEquals(l.size(), 11)
    }

    @Test
    fun get() {
        val l = MyMutableIntList()
        l.add(99)
        for (i in 0..<10) {
            l.add(i)
        }
        l.add(412)
        assertEquals(l[0], 99)
        assertEquals(l[11], 412)

    }

    @Test
    fun set() {
        val l = MyMutableIntList()
        l.add(44)
        l[0] = -424
        assertEquals(l[0], -424)
    }
}