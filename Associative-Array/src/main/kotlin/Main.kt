
//Class that is literally a pair holding a key and a value. Used in place of generic Pairs
class KeyValuePair<K, V>(val key: K, var value: V)

class AssociativeArray<K, V> {

    private var capacity = 8 //max size of array
    private var size = 0 //Actual size of array
    private var buckets = Array<MutableList<KeyValuePair<K, V>>>(capacity) { mutableListOf() }


    /**
     * Hash Function
     *
     * Currently only using Modulo
     */
    private fun hashFunction(key: K): Int {
        return key.hashCode() % capacity
    }

    /**
     * Insert the mapping from the key, [k], to the value, [v].
     * If the key already maps to a value, replace the mapping.
     */
    operator fun set(key: K, value: V) {

        //Seperate Chaining
        val bucketIndex = hashFunction(key)
        val bucket = buckets[bucketIndex]

        // Check if key already exists, if so, update the value
        for (element in bucket) {
            if (element.key == key) {
                element.value = value
                return
            }
        }

        // Add new key-value pair
        bucket.add(KeyValuePair(key, value))
        size++

        // Resize if load factor exceeds 0.75
        if (size.toDouble() / capacity > 0.75) {
            resize()
        }
    }
    /**
     * @return the value associated with the key [k] or null if it doesn't exist
     */
    fun get(key: K): V? {
        val bucketIndex = hashFunction(key)
        val bucket = buckets[bucketIndex]

        for (element in bucket) {
            if (element.key == key) {
                return element.value
            }
        }
        //Can't find it
        return null
    }
    /**
     * Remove the key, [k], from the associative array
     * @param k the key to remove
     * @return true if the item was successfully removed and false if the element was not found
     */
    fun remove(key: K): Boolean {
        val bucketIndex = hashFunction(key)
        val bucket = buckets[bucketIndex]

        for (element in bucket) {
            if (element.key == key) {
                bucket.remove(element)
                size--
                return true
            }
        }
        return false
    }

    /**
     * @return the number of elements stored in the hash table
     */
    fun size(): Int {
        return size
    }

    /**
     * @return true if [k] is a key in the associative array
     */
    fun contains(key: K): Boolean {
        val bucketIndex = hashFunction(key)
        val bucket = buckets[bucketIndex]

        for (element in bucket) {
            if (element.key == key) {
                return true
            }
        }
        return false
    }
    /**
     * @return the full list of key value pairs for the associative array
     */
    fun keyValuePair(): List<Pair<K, V>> {
        val keyList = mutableListOf<Pair<K, V>>()
        for (bucket in buckets) {
            for (element in bucket) {
                keyList.add(Pair(element.key, element.value))
            }
        }
        return keyList
    }


    /**
     * Extra function for resizing the associative array when we've hit capacity. Called only when inserting new key & value and we don't have space.
     *
     *
     */
    private fun resize() {
        capacity *= 2
        val newBuckets = Array<MutableList<KeyValuePair<K, V>>>(capacity) { mutableListOf() }

        for (bucket in buckets) {
            for (element in bucket) {
                val newIndex = hashFunction(element.key)
                newBuckets[newIndex].add(element)
            }
        }

        buckets = newBuckets
    }
}

fun main() {
    val myAssociativeArray = AssociativeArray<String, Int>()

    myAssociativeArray.set("One", 1)
    myAssociativeArray.set("Two", 2)
    myAssociativeArray.set("Three", 3)
    myAssociativeArray.set("Four", 4)
    myAssociativeArray.set("Five", 5)

    println("Keys: ${myAssociativeArray.keyValuePair()}")

    println("Value for key 'Two': ${myAssociativeArray.get("Two")}")

    println("Removing key 'Three'")
    myAssociativeArray.remove("Three")

    println("Is key 'Three' present? ${myAssociativeArray.contains("Three")}")

    println("Size of array: ${myAssociativeArray.size()}")
}
