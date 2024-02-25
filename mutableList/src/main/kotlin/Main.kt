package org.example


class MyMutableIntList {


    //buffersize == myArray.size
    private var bufferSize = 2
    private var arraySize = 0  //Actual lemgth of list
    private var myArray = Array<Int?>(bufferSize) { null }


    /**
     * Add [element] to the end of the list
     */
    fun add(element: Int) {

        arraySize += 1


        //Want to double the actual size of th elist & then copy + append
        if (myArray.size < arraySize) {
            bufferSize *= 2

            //new copy of an array
            myArray = myArray.copyOf(bufferSize)

            myArray.set(arraySize - 1, element)

            myArray

        } else {
            //JUST APPEND
            myArray.set(arraySize - 1, element)


        }

    }

    /**
     * Remove all elements from the list
     */
    fun clear() {
        bufferSize = 2
        arraySize = 0
        myArray = Array<Int?>(bufferSize) { null }


    }

    /*
     * @return the size of the list
     */
    fun size(): Int {
        return arraySize

    }

    /**
     * @param index the index to return
     * @return the element at [index]
     */
    operator fun get(index: Int): Int? {
        //If the index is not in the Array size, throw an exception for out of bounds
        if (index > bufferSize - 1) {
            throw Exception("Out of Bounds")

        } else {
            return myArray.get(index)
        }
    }

    /**
     * Store [value] at position [index]
     * @param index the index to set
     * @param value to store at [index]
     */
    operator fun set(index: Int, value: Int) {
        myArray[index] = value

    }


}
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {

    var array: MyMutableIntList = MyMutableIntList()
    array.add(20)
    println(array.get(0))
    array.add(4)
    array.set(1, 6)
    println(array.get(1))
    println(array.size())

    array.add(12)
    array.add(14)
    println(array.get(6))


}