# Parallel Algorithms In Kotlin 

Throughout DSA, majority of the algorithms are by nature, serial algorithms. My final project is to thus explore Fork-join parallel algorithms and see whether it's performance is better than it's serial counterparts. 


## Parallel Processing and in Kotlin (Coroutines)

In Kotlin, to achieve multi-threading, we use Coroutines, an official Kotlin library. In their own words, a coroutine is:

"A coroutine is an instance of a suspendable computation. It is conceptually similar to a thread, in the sense that it takes a block of code to run that works concurrently with the rest of the code. However, a coroutine is not bound to any particular thread. It may suspend its execution in one thread and resume in another one."



### Kotlinx.coroutine and Dependencies

I'd like to take a second and note that Kotlinx.coroutine is an official library, you do need to add it as a dependency in your build file to successfully import it. On the official guide for Gradle, `build.gradle.kts` they tell you that the proper way to add it to dependencies is:
```
dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1-Beta")
}
```

for me, using the IntelliJ IDE and with Gradle, I found that 
```
dependencies {
    implementation("org.jetbrains.kotlinx", "kotlinx-coroutines-core", "1.7.3")
}
```

worked for me. If you still get a "unreferenced" error for Kotlinx.coroutine after adding and successfully building, just restart the IDE and it should fix that error. 
## Implementing Algorithms using Parallel Processing

### MergeSort

The first algorithm we can refactor to utilize parallel processing is mergesort. 

### Matrix Multiplication

There are two ways to implement Matrix Multiplication that has been explored so far in the course: Brute Force and Strassen's Algorithm
For this project, I also wanted to implement a method that reduces cache misses and also a method using parallel processing to see the performances of all four different types. 






## Benchmarking

The main exploration for this project was benchmarking matrix multiplication. For this benchmarking, I compared 4 different implementations of Matrix multiplication.

1. Brute Force
2. Strassen's Algorithm, serial implementation
3. Strassen's Algorithm, parallel implementation
4. Brute Force using Multik's array & dot function


For the first three implementations, I used my own class that I wrote, `Matrix`, with it's own add and subtract functions. Furthermore, it uses Kotlin's Array data type to construct a 2D array. 

For the last implementation, I was curious whether using an external library, would make an impact. In real world application, most developers would already rely on external libraries for most complex operations, so I was curious whether writing a matrix multiplication implementation from scratch is actually worth it. Multik is a multidimensional array library. I has it's own 2D array data type and has a built in matrix multiplication function called `dot`. 

Interestingly, the 2D array data type (NDarray) is actually just an overglorified IntArray. However, the dot function, is actually from the LinAlg library which is originally a Python library with a kotlin binding interface. 
## Conclusions & Limitations



## Annotated Bibliography

The resources I used to guide me were:

-[An article explaining Kotlin Coroutines](https://betterprogramming.pub/parallelization-in-kotlin-with-coroutines-91f0c77c5a8)
- [Introduction to Algorithms CLRS 4th Edition](https://dl.ebooksworld.ir/books/Introduction.to.Algorithms.4th.Leiserson.Stein.Rivest.Cormen.MIT.Press.9780262046305.EBooksWorld.ir.pdf)
-[Official Kotlin Coroutine guide](https://kotlinlang.org/docs/coroutines-basics.html)
-[Kotlinx Coroutine Github Page & README](https://github.com/Kotlin/kotlinx.coroutines/blob/master/README.md#using-in-your-projects)
-[Kotlin Multik's Github Page & dot function implementation](https://github.com/Kotlin/multik/blob/79dedd1cba815a4c1aa3d34c51646adc4abbd91b/multik-core/src/commonMain/kotlin/org/jetbrains/kotlinx/multik/api/linalg/dot.kt#L4)
