# Parallel Algorithms In Kotlin 

Throughout DSA, majority of the algorithms are by nature, serial algorithms. My final project is to thus explore Fork-join parallel model algorithms and see whether it's performance is better than it's serial counterparts. 

The Fork-Join Parallel Model is a style of programming where the code is designed so that you split up many minor tasks to be done in parallel (hence forked), and then eventually merge them back together (hence joined). This trait is often inherent in recursive and divide and conquer algorithms, as you could fork parallel sections recursively or the subsections in a divide and concquer algorithm. When designing a fork-join parallel model, it should be almost identical in structure to it's serial counterpart, which we call the "serial projection".

For this project, I examine the impact of parallel processing on two algorithms we've learned in this course so far: the Mergesort Algorithm and Matrix Multiplication (specifically Strassen's Algorithm).

## Parallel Processing and in Kotlin (Coroutines)

In Kotlin, to achieve multi-threading, we use Coroutines, an official Kotlin library. In their own words, a coroutine is:

"an instance of a suspendable computation. It is conceptually similar to a thread, in the sense that it takes a block of code to run that works concurrently with the rest of the code. However, a coroutine is not bound to any particular thread. It may suspend its execution in one thread and resume in another one."


### Kotlinx.coroutine and Dependencies

I'd like to take a second and note that Kotlinx.coroutine is an official library,so you do need to add it as a dependency in your build file to successfully import it. On the official guide for Gradle, `build.gradle.kts` they tell you that the proper way to add it to dependencies is:
```
dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1-Beta")
}

```
 
If you still get a "unreferenced" error for Kotlinx.coroutine after adding and successfully building, just restart the IDE and it should fix that error. For more in depth investigation on how to use Coroutines, Kotlin has an official guide on using Kotlinx.coroutine which is very helpful. In general, you can fork a process and specify which line of code through the `async {}` block of code and use the `.await()` attribute to get the results of the thread. Any function with parallel processing needs to be a `suspend` type and you can delinate what part of code is serial and parallel through `runBlocking{}`. 

The official resources are extremely comprehensive and have been linked in the biliography, but one semantic that I will go into is the fact that there is a slight different between using `runBlocking` and `coroutineScope`, in that `runBlocking` also blocks a thread from continuing until the current process has been finished, thus preventing any potential issues if multiple threads are accessing the same data. `coroutineScope` does not block any threads from running. In general, there is no performance difference between the two (when I refactored Strassen's parallel algorithm using `runBlocking` and `coroutineScope` neither behaved differently during performance benchmarking). However, for mergeSort parallel processing, I had to use `coroutineScope` as I would get errors about missing classes if I used `runBlocking`. There doesn't seem to be a clear explanation why size of the list triggers this, but my assumption is that `runBlocking` is not meant for massive threading as, unlike `coroutineScope`

## Implementing Algorithms using Parallel Processing

### MergeSort

The first algorithm we can refactor to utilize parallel processing is Mergesort. Earlier in the course we learned about how to mergesort works as a divide and conquer algorithm. Mergesort is relatively easy to refactor to be parallel, and after that I compared it to the serial version during benchmarking. My specific implementation of mergesort is recursive for both its serial and parallel implementation. 


Again, during my implementation, one thing I discovered is that for Mergesort it matters that you use coroutineScope rather than runBlocking. RunBlocking effectively blocks threads until the thread is done, while coroutineScope is a more free for all. 

#### Benchmarking

Below is a benchmark comparing the serial and parallel versions of Mergesort. The code for the functions are located in files `mergesort-serial.kt` and `mergesort-parallel.kt`


The results are that 

![mergesort benchmarking](mergesortBenchmarking.jpg)

**Fig 1** Shows the benchmarking done for the Mergesort algorithm, comparing it's serial and parallel implementations. Surprisingly, parallel processing does not greatly impact the performance of Mergesort and in fact it's serial projection is more useful until list sizes of around 100000 and above. Overall, the Mergesort algorithm did not greatly benefit from using a parallel processing implementation.  

### Matrix Multiplication

There are two ways to implement Matrix Multiplication that has been explored so far in the course: Brute Force and Strassen's Algorithm
For this project, I decided to go a little deeper than just implement Strassen's Algorithm in a parallel manner, but also compare all three methods with an external library for linear algebra called Multik. Multik is a Kotlin library based off of LinAlg, a library based on C++ which uses Basic Linear Algebra Subprograms (BLAS). At it's core, BLAS is a set of assembly instructions and thus tailored for performance. In short, the very nature of Multik's matrix multiplication has been written to capitalize on it's low level affinity and maximize performance. 


First, I refactored the serial version of Strassen's algorithm to use coroutines. Next, I also implemented a method that uses Multik's 2D arrays and dot function instead of my Matrix object and mutliplication functions. All of these different implementations can be found in the `matrix-multiplication-basic.kt` file.

Below is the Strassen Parallel Implementation function:

```
suspend fun strassenMultiplyParallel(other: Matrix): Matrix? = runBlocking {
        val matrix2 = other.matrix
        //If size doesn't work
        if (matrix.size != other.matrix.size){
            return@runBlocking null
        }

        //Base Case 1x1 works
        if (matrix.size == 1 && other.matrix.size == 1){
            val tempMatrix = Matrix(1)
            tempMatrix.matrix[0][0]= matrix[0][0]?.times(other.matrix[0][0]!!)
            return@runBlocking tempMatrix

        }

        //Seperating into the 4 quadrants
        val a = Matrix(size/2)
        for (i in 0..size/2 -1){
            a.matrix[i] = matrix[i].slice(0..size/2 -1).toTypedArray()
        }

        val b = Matrix(size/2)
        for (i in 0..size/2 -1){
            b.matrix[i] = matrix[i].slice(size/2..size -1).toTypedArray()
        }

        val c = Matrix(size/2)
        for (i in size/2..size -1){
            c.matrix[i - size/2] = matrix[i].slice(0..size/2 -1).toTypedArray()
        }

        val d = Matrix(size/2)
        for (i in size/2..size -1){
            d.matrix[i - size/2] = matrix[i].slice(size/2..size -1).toTypedArray()
        }


        val e = Matrix(size/2)
        for (i in 0..size/2 -1){
            e.matrix[i] = matrix2[i].slice(0..size/2 -1).toTypedArray()
        }

        val f = Matrix(size/2)
        for (i in 0..size/2 -1){
            f.matrix[i] = matrix2[i].slice(size/2..size -1).toTypedArray()
        }

        val g = Matrix(size/2)
        for (i in size/2..size -1){
            g.matrix[i- size/2] = matrix2[i].slice(0..size/2 -1).toTypedArray()
        }

        val h = Matrix(size/2)
        for (i in size/2..size -1){
            h.matrix[i - size/2] = matrix2[i].slice(size/2..size -1).toTypedArray()
        }

        //Doing the 7 calculations
        val tempMatrix = Matrix(size)
        val m1 = async { a.add(d)?.strassenMultiply(e.add(h)) }
        val m2 = async { c.add(d)?.strassenMultiply(e) }
        val m3 = async { a.strassenMultiply(f.subtract(h)) }
        val m4 = async { d.strassenMultiply(g.subtract(e)) }
        val m5 = async { a.add(b).strassenMultiply(h) }
        val m6 = async { c.subtract(a).strassenMultiply(e.add(f)) }
        val m7 = async { b.subtract(d).strassenMultiply(g.add(h)) }


        //Adding them together
        val c11 = m1.await()!!.add( m4.await()!!).subtract( m5.await()!!).add( m7.await()!!)
        val c12 = m3.await()!!.add(m5.await()!!)
        val c21 = m2.await()!!.add( m4.await()!!)
        val c22 = m1.await()!!.subtract(m2.await()!!).add( m3.await()!!).add( m6.await()!!)

        for (i in 0 until size/2){
            if (c11 != null) {
                for (j in 0 until size/2)
                    tempMatrix.matrix[i][j] = c11.matrix[i][j]
            }
        }

        for (i in 0 until size/2){
            if (c12 != null) {
                for (j in size/2 until size)
                    tempMatrix.matrix[i][j] = c12.matrix[i][j - size/2]
            }
        }

        for (i in size/2 until size){
            if (c21 != null) {
                for (j in 0 until size/2)
                    tempMatrix.matrix[i][j] = c21.matrix[i- size/2][j]
            }
        }

        for (i in size/2 until size){
            if (c22 != null) {
                for (j in size/2 until size)
                    tempMatrix.matrix[i][j] = c22.matrix[i- size/2][j - size/2]
            }
        }
        return@runBlocking tempMatrix
    }

```

## Benchmarking

The main exploration for this project was benchmarking matrix multiplication. For this benchmarking, I compared 4 different implementations of Matrix multiplication.

1. Brute Force
2. Strassen's Algorithm, serial implementation
3. Strassen's Algorithm, parallel implementation
4. Brute Force using Multik's array & dot function


For the first three implementations, I used my own class that I wrote, `Matrix`, with it's own add and subtract functions. Furthermore, it uses Kotlin's Array data type to construct a 2D array. 

For the last implementation, I was curious whether using an external library, would make an impact. In real world application, most developers would already rely on external libraries for most complex operations, so I was curious whether writing a matrix multiplication implementation from scratch is actually worth it. Multik is a multidimensional array library. I has it's own 2D array data type and has a built in matrix multiplication function called `dot`. Interestingly, the 2D array data type (NDarray) is actually just an overglorified IntArray. However, the dot function, is actually from the LinAlg library which uses BLAS as explained above. 

For benchmarking Matrix multiplication, I decided to investigate both the performance of the different implementations based on time as well as heap memory usage, all located in the `matrixBenchmark.kt` file. I benchmarked with matrice sizes of 1, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, and  8192 with randomly generated integers in each array index. 
Below are the functions used to benchmark both time performance and memory usage. 

```

/**
 * Function for measuring how long code takes to execute
 *
 * @param function a function/line of code that will be executed that we're measuring the speed of
 *
 *@return Pair, pair.first is the result of the function and the pair.second is the actual runtime it took to run the code
 */
inline fun<T> measureTimeMillisPair(function: () -> T): Pair<T, Long> {
    val startTime = System.currentTimeMillis()
    val result: T = function.invoke()
    val endTime = System.currentTimeMillis()

    return Pair(result, endTime - startTime)
}

```


```

/**
 * Function that calculates how much memory has been used (or unused)
 *
 * @return A long int value which is how much heap memory was used in bytes
 */
fun memoryUsed(): Long{
    val memoryMXBean: MemoryMXBean = ManagementFactory.getMemoryMXBean()
    val heapMemoryUsage = memoryMXBean.heapMemoryUsage
    return heapMemoryUsage.used

}

/**
 * Function that calculates the total bytes (not megabytes) of memory used by the program throughout the duration of execution
 * @param function a function/line of code that will be executed that we're measuring the speed of
 *
 *  @return Pair, pair.first is the result of the function and the pair.second is the actual bytes that it used in memory to run
 *
 */
inline fun <T> measureMemoryBytes(function: () -> T): Pair<T?, Long> {
    System.gc()

    val initialMemory = memoryUsed()

    val result: T = function.invoke()
    val finalMemory = memoryUsed()

    val memoryUsage = finalMemory - initialMemory

    return Pair(result, memoryUsage)
    }
```


Below are the graphed results of the benchmarking.


![matrix-time](matrix-time.jpg)

**Fig 2** Shows benchmarking done in relation to matrix size and it's affect on the time it takes to do matrix multiplication. For matrices size 1-64, Brute force is the most time efficient, and then afterwards being the Multik library implementation. Overall, the parallel implementation of Strassen's algorithm is the most time efficient out of all of the local implementations at matrix sizes 2048, with brute force being the ideal algorithm for any sizes before 2048. 


![matrix-memory](matrix-memory.jpy)

**Fig 3** Shows benchmarking done in relation to matrix size and the amount of memory usage from the heap for matrix multiplication. Brute force consumes the least amount of memory usage for matrices sized 512 or lower. Afterwards the Multik library is the best method, and Brute Force being the best out of the local implementations. This is reasonable as recursive functions often take more memory in general. Furthermore, you could also posit that the Brute Force method reduces cache misses since it goes row by row, meaning that it would be more efficient in terms of memory usage. 



## Conclusions & Limitations

The conclusions on implementing parallel processing (specifically using the fork-join model) to improve the performance of alogrithms like Mergesort and Matrix multiplication has been found to be notable effective in certain conditions. However, one must be very cognizant of memory limitations when introducing parallel processing as the spatial complexity of an algorithm may worsen due to threads rather than be improved. Furthermore, at the end of the parallel processing will only be truly significant on large datasets. Examples of this can be applied to supercomputing and cloud computing, but for daily local usage it is probably not worth investing in this, especially in the context of Kotlin which is often catered to local development rather than large scale development. 


Of course, there are several limitations to these conclusions. As shown by the Multik library, Kotlin is not a language that is conducive to being able to optimize due to the level of abstraction. It would be much more interesting to compare these with different coding languages, like C++ or Python, and see how optimization and performance are impacted. Furthermore, as shown by Mergesort compared to matrix multiplication, parallel processing does not improve all algorithms equally and it would be very unwise to assume that parallel processing is the best answer to these problems. It's improtant to calculate the thresholds and switch implementations based on the size of the data, and in the case of Mergesort, it really doesn't matter til list sizes become notably large. Parallel processing algorithms are a novel twist that can provide significant performance advantages, but it is never guaranteed as it relies on a multitude of factors.


## Annotated Bibliography

The resources I used to guide me were:

* [An article explaining Kotlin Coroutines](https://betterprogramming.pub/parallelization-in-kotlin-with-coroutines-91f0c77c5a8)
* [Introduction to Algorithms CLRS 4th Edition](https://dl.ebooksworld.ir/books/Introduction.to.Algorithms.4th.Leiserson.Stein.Rivest.Cormen.MIT.Press.9780262046305.EBooksWorld.ir.pdf)
* [Official Kotlin Coroutine guide](https://kotlinlang.org/docs/coroutines-basics.html)
* [Kotlinx Coroutine Github Page & README](https://github.com/Kotlin/kotlinx.coroutines/blob/master/README.md#using-in-your-projects)
* [Kotlin Multik's Github Page & dot function implementation](https://github.com/Kotlin/multik/blob/79dedd1cba815a4c1aa3d34c51646adc4abbd91b/multik-core/src/commonMain/kotlin/org/jetbrains/kotlinx/multik/api/linalg/dot.kt#L4)
