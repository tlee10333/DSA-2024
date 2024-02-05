/**
 * Double Linked List Implementation in Kotlin56
 *
 * @param T data is stored
 * @property top is a stackNode that is on the top of the stack
 * @property bottom is a stackNode that is on the bottom of the stack
 *
 */
class doubleList<T>(){

    internal var top: StackNode<T>? = null
    internal var bottom: StackNode<T>? = null

    /**
     * Adds the element [data] to the front of the linked list. Checks if that there is only one element in the list then bottom & top will both point to null
     *
     * @param data is the value of the new element in the list
     */
    fun pushFront(data: T) {
        val tmp:StackNode<T> = StackNode<T>(data, top, null)
        top?.before = tmp
        top = tmp

        if (bottom == null){
            bottom = top
        }

    }

    /**
     * Adds the element [data] to the back of the linked list. Checks if that there is only one element in the list then bottom & top will both point to null
     *
     * @params data is the value of the new element in the list
     */
    fun pushBack(data: T) {
        val tmp = StackNode<T>(data, null, bottom)
        bottom?.next = tmp
        bottom = tmp

        if (top == null){
            top = bottom
        }

    }
    /**
     * Removes an element from the front of the list. If the list is empty, it is unchanged. Also accounts for if there is only 1 element in the list.
     * @return the value at the front of the list or nil if none exists
     */
    fun popFront(): T? {
        val tmp = peekFront()
        top = top?.next
        top?.before = null


        // Also accounts for if there is only 1 element in the list.
        if (top?.next == null){
            bottom = top
        }

        return tmp


    }
    /**
     * Removes an element from the back of the list. If the list is empty, it is unchanged. Also accounts for if there is only 1 element in the list.
     * @return the value at the back of the list or nil if none exists
     */
    fun popBack(): T? {
        val tmp = peekBack()
        bottom = bottom?.before
        bottom?.next = null
        //Also accounts for if there is only 1 element in the list.
        if (top?.before == null){
            top = bottom
        }
        return tmp

    }
    /**
     * @return the value at the front of the list or nil if none exists
     */
    fun peekFront(): T? {
        return top?.data //returns null if data is null. Returns data if not null


    }

    /**
     * @return the value at the back of the list or nil if none exists
     */
    fun peekBack(): T? {
        return bottom?.data

    }

    /**
     * @return true if the list is empty and false otherwise
     */
    fun isEmpty(): Boolean {
        return top == null

    }

}

/**
 * A class used to represent each element in the linked list.
 *
 * @param T is the type of data stored
 * @property data is data stored in the element associated with this stackNode
 * @property next is a StackNode object that points to the next element in the linked list respective to the stackNode
 * @property before is a StackNode object that points to the element before the current element in the linked list
 */
class StackNode<T>(val data: T?, var next: StackNode<T>?, var before: StackNode<T>?) {


    /**
     * Setter method for the next property
     * @params newNext is the new next value
     */
    fun newNext(newNext: StackNode<T>){
        next = newNext
    }

    /**
     * Setter method for the before property
     * @params newBefore is the new before value
     */
    fun newBefore(newBefore: StackNode<T>?){
        before = newBefore
    }

}



//LIFO
/**
 * LIFO interface for a Stack class using the doubleList class
 */
interface Stack<T> {
    /**
     * Add [data] to the top of the stack
     */
    fun push(data: T)
    /**
     * Remove the element at the top of the stack.  If the stack is empty, it remains unchanged.
     * @return the value at the top of the stack or nil if none exists
     */
    fun pop(): T?
    /**
     * @return the value on the top of the stack or nil if none exists
     */
    fun peek(): T?
    /**
     * @return true if the stack is empty and false otherwise
     */
    fun isEmpty(): Boolean
}


//FIFO
/**
 * FIFO interface for a Queue class using the doubleList class
 */
interface Queue<T> {
    /**
     * Add [data] to the end of the queue.
     */
    fun enqueue(data: T)
    /**
     * Remove the element at the front of the queue.  If the queue is empty, it remains unchanged.
     * @return the value at the front of the queue or nil if none exists
     */
    fun dequeue(): T?
    /**
     * @return the value at the front of the queue or nil if none exists
     */
    fun peek(): T?
    /**
     * @return true if the queue is empty and false otherwise
     */
    fun isEmpty(): Boolean
}

/**
 * A class that implements the Stack interface
 * @param T type of data stored
 * @property dlist is a instance of a doubleList configured as a stack
 */
class doubleStack<T> : Stack<T> {
    val dlist: doubleList<T> = doubleList()
    /**
     * Add [data] to the top of the stack
     */
    override fun push(data: T) {
        dlist.pushFront(data)
    }
    /**
     * @return the value on the top of the stack or nil if none exists
     */
    override fun pop(): T? {
        dlist.popFront()
        return dlist.peekFront()

    }
    /**
     * @return the value on the top of the stack or nil if none exists
     */
    override fun peek(): T?{
        return dlist.peekFront()
    }
    /**
     * @return true if the stack is empty and false otherwise
     */
    override fun isEmpty(): Boolean {
        return dlist.isEmpty()
    }

}

/**
 * A class that implements the Queue interface
 * @param T type of data stored
 * @property dlist is a instance of a doubleList configured as a queue
 */
class doubleQueue<T> : Queue<T> {
    val dlist: doubleList<T> = doubleList()

    /**
     * Add [data] to the end of the queue.
     */
     override fun enqueue(data: T) {
         dlist.pushBack(data)

    }
    /**
     * Remove the element at the front of the queue.  If the queue is empty, it remains unchanged.
     * @return the value at the front of the queue or nil if none exists
     */
    override fun dequeue(): T? {

        dlist.popFront()
        return dlist.peekFront()

    }
    /**
     * @return the value at the front of the queue or nil if none exists
     */
    override fun peek(): T? {
        return dlist.peekFront()

    }
    /**
     * @return true if the queue is empty and false otherwise
     */
    override fun isEmpty(): Boolean {
        return dlist.isEmpty()

    }


}

fun main() {

    //Stack-Queue Problem P3

    var rStack:  doubleStack<String> = doubleStack()
    //Actual order of stack is: (top)  4 3 2 1 (bottom)
    rStack.push("one")
    rStack.push("two")
    rStack.push("three")
    rStack.push("four")

    val newStack: doubleStack<String> = doubleStack()

    //Method 1 (Least efficient & O(n)
    while (!rStack.isEmpty()){
        rStack.peek()?.let { newStack.push(it) }
        rStack.pop()
    }
    rStack = newStack


    println(rStack.peek())
    newStack.pop()
    println(rStack.peek())
    newStack.pop()
    println(rStack.peek())
    newStack.pop()
    println(rStack.peek())






}