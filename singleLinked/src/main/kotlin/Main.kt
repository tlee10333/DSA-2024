package org.example


/**
 * An implementation o a LIFO Stack structure
 * @param T data stored in the stack is a T
 * @property top is a stacknode that is at the top of the stack
 *
 *
*/

class Stack<T> {
    internal var top: StackNode<T>? = null


    /**
     * Push [data] onto the stack
     * @param data the new value to put onto the stack
     *
     */
    fun push(data:T){
        top = StackNode<T>(data, top)
    }


    /**
     * Pop off/remove the top data from the queue
     *
     * @return the top element of the stack, null if otherwise
     */
    fun pop(): T? {
        val tmp = peek()
        top = top?.next
        return tmp

    }

    /**
     * Peek at the top data in the stack
     *@return the top element of the stack, null if otherwise
     */
    fun peek(): T? {
        return top?.data //returns null if data is null. Returns data if not null

    }

    /**
     * Checks if the stack is empty
     *
     * @return True if stack is empty/null and false otherwise
     */
    fun isEmpty(): Boolean {
        return top == null
    }

}

class StackNode<T>(val data: T, var next: StackNode<T>?) {

}

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val s: Stack<String> = Stack()
    s.push( "hello")
    println(s.pop())
    println(s.pop())
}