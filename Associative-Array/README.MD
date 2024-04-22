# Associative Array Writeup



## Associative Array

The Associative Array I designed was a pretty basic associative array using a hash map and buckets to organize data. We also have a set resize threshold so that once the array hits its capacity, it will automatically double the size when generating a new associative array to copy the data to. This should hypothetically decrease the amount of time complexity wasted on copying arrays when resizing. 


The only other notable trait about the associative array is that while I use the built in `hashCode()` function, I also ensure that the result of the hash function is always positive as it should correlate with the possible indexes in the array. 


## Markov 


For the implementation, I decided to create a Markov Text Generator using my associative array. I think that it was a fun challenge and it was really interesting because you could write a Markov text generator in Kotlin using their dictionary. However, if you attempted to go down that route, it would be in general much more tedious due to the numerous null-checks you'd be required to do. When using the associative array, it was much more simple and straightforward because the class already takes into account null returns and other values. This way, the code for the Markov is much easier to read and thus more pleasant to work with. 


In the `Markov.kt` file, which is where the Markov implmentation lives, there is a main function with sample text and code that generates 2 random Markov sentences. Running the code multiple times proves that you can get different results. For example, 



```{


fun main() {

    //Sample Markov Text Generator
    var list = "Lorem Ipsum. The sky is blue. Let's all Lorem Ipsum. Are you hungry? Fun fun times. This is fun. I don't know what I'm doing, but Lorem Ipsum all the way! Music to my ears, but I'm also too hungry to care. Once upon a time, I slumbered away in fun dreams. "
    var randomSentence = generate_text(list, 4)
    println(randomSentence)
}



}```



OUTPUT: 


```{

I slumbered away in fun dreams. Music to my ears, but I'm also too hungry to care. I don't know what I'm doing, but Lorem Ipsum. I slumbered away in fun dreams.

}```


Which is follows the code. Given the raw text, we generate 4 sentences which in general make sense and seem cohesive.
