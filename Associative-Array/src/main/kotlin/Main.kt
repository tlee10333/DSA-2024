
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
        val arrayBucket = buckets[bucketIndex]

        //  if key already exists, update  value
        for (element in arrayBucket) {
            if (element.key == key) {
                element.value = value
                return
            }
        }

        // Add new key-value pair
        arrayBucket.add(KeyValuePair(key, value))
        size++

        // Resize if size hits capacity
        if (size == capacity) {
            capacity *= 2
            val newBuckets = Array<MutableList<KeyValuePair<K, V>>>(capacity) { mutableListOf() }

            for (bucket in buckets) {
                for (element in bucket) {
                    //copy every single value to the new associative array
                    val newIndex = hashFunction(element.key)
                    newBuckets[newIndex].add(element)
                }
            }

            buckets = newBuckets
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



}

