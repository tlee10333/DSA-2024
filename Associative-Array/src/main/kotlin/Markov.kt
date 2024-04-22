
import kotlin.random.Random

import org.example.AssociativeArray
import org.example.KeyValuePair


//function that just takes a String and will remove any \n and then split all the words into a list based on whitespace.
fun build_word_list(source_text: String) = source_text.replace("\n", " ").split("\\s+".toRegex())

//build a map for words that come after a specific word
fun build_next_words(word_list: List<String>):  AssociativeArray<String, MutableList<String>> {
    /*Function that generates a map with unique words from the word_list. Each key has a list of words which
    come after the key in the word_list */
    val unique_words = word_list.toSet() //Create a Set of all keywords
    var wordmap =AssociativeArray<String, MutableList<String>>()

    //Also add one key that's blank. This will be for the first word of a sentence.
    wordmap.set("",  mutableListOf<String>())
    //Generating the wordmap keys (empty)
    for (word in unique_words){
        wordmap.set(word, mutableListOf<String>())

    }
    //Manually input the first word in our word_list into the blank key.
    wordmap.get("")?.add(word_list[0])

    //fill the values for the key
    for (index in 0..word_list.count()-2){

        //For any words that are at the end of the sentence,
        //look at the next word & place them empty key because they start a sentence.
        if (
            word_list[index].contains(".")  or
            word_list[index].contains("!") or
            word_list[index].contains("?")

        ) {
            /**To successfully update a mutable list nested in a mutable map, you need to first use the getOrPut
             * function to access/call the value based on the key, and then use the add method from the mutable list
             * class. If you don't do this and directly try to just use the map's key and do wordmap[key].add() you will
             * get a type mismatch because MutableList<String>? and MutableList<String> are 2 different things. I don't
             * get it. Just using the .put() function will give u an error about non-null assered calls.
             **/

            wordmap.get("")?.add(word_list[index+1])

        } else{
            //All other words that come after a keyword are appended to their respective keys
            wordmap.get(word_list[index])?.add(word_list[index+1])

        }
    }

    return wordmap

}

fun generate_sentence(next_words: AssociativeArray<String, MutableList<String>> ): String {

    /*Generates a single random Markov sentence from a map of words */

    var new_sentence = mutableListOf<String>()
    var key = ""
    var choice_range = next_words.get(key)

    if (choice_range != null) {
        while (choice_range?.count() != 0){
            new_sentence.add(choice_range?.get(Random.nextInt(choice_range!!.count())).toString())
            key = new_sentence[new_sentence.count()-1]
            choice_range = next_words.get(key)
        }
    }
    return new_sentence.joinToString(separator=" ")
}

fun generate_text(words: String, num_sentences: Int): String {
    /*Generates random Markov text. Generates total number of sentences based on num_sentences
    parameter */
    var next_words = build_next_words(build_word_list(words))
    val text = mutableListOf<String>()
    for (num in 0..num_sentences-1) {
        text.add(generate_sentence(next_words))
    }
    return text.joinToString(separator=" ")
}

fun main() {

    //Sample Markov Text Generator
    var list = "Lorem Ipsum. The sky is blue. Let's all Lorem Ipsum. Are you hungry? Fun fun times. This is fun."
    var randomSentence = generate_text(list, 2)
    println(randomSentence)
}