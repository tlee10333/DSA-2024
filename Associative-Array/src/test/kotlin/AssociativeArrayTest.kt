import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

import org.example.AssociativeArray
import org.example.KeyValuePair


class AssociativeArrayTest {

    //Test that the KeyValuePair class can how a pair containing a key and a value
    @Test
    fun KeyValuePair(){

        //Test that We can correctly initialize values for the keys and values\
        var key = "key"
        var value = "value"
        var testPair = KeyValuePair(key, value)
        assertEquals(key, testPair.key)
        assertEquals(value, testPair.value)

        //Test that the value is mutable
        value = "new_value"

        testPair.value = value
        assertEquals(value, testPair.value)

    }

    @Test
    fun setAndGet() {
        //Test that we can successfully set
        val myAssociativeArray = AssociativeArray<String, Int>()
        myAssociativeArray.set("One", 1)
        assertEquals(1, myAssociativeArray.get("One"))

        //Successfully set multiple keys and it won't be overwritten
        myAssociativeArray.set("Two", 2)
        assertEquals(2, myAssociativeArray.get("Two"))

        //Adding duplicates
        myAssociativeArray.set("Two", 10)
        assertEquals(10, myAssociativeArray.get("Two"))
    }


    @Test
    fun remove() {
        //Test that we can successfully remove a key value pair
        val myAssociativeArray = AssociativeArray<String, Int>()
        myAssociativeArray.set("One", 1)
        myAssociativeArray.set("Two", 2)
        myAssociativeArray.remove("One")
        assertNull(myAssociativeArray.get("One"))


    }

    @Test
    fun size() {
        val myAssociativeArray = AssociativeArray<String, Int>()

        myAssociativeArray.set("One", 1)
        myAssociativeArray.set("Two", 2)
        myAssociativeArray.set("Three", 3)
        myAssociativeArray.set("Four", 4)
        myAssociativeArray.set("Five", 5)

        //Test that it has the correct size
        assertEquals(5, myAssociativeArray.size())
        //Test that it has the correct size even after removing
        myAssociativeArray.remove("One")
        assertEquals(4, myAssociativeArray.size())    }

    @Test
    fun contains() {
        //Test that we can successfully see if a key value pair exists or not
        val myAssociativeArray = AssociativeArray<String, Int>()
        myAssociativeArray.set("One", 1)
        assertTrue(myAssociativeArray.contains("One"))
        assertFalse(myAssociativeArray.contains("Two"))


    }

}