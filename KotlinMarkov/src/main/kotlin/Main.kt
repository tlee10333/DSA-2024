
import kotlin.random.Random
import kotlin.random.nextInt

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

//functions

fun build_word_list(source_text: String) = source_text.replace("\n", " ").split("\\s+".toRegex())

//build a map for words that come after a specific word
fun build_next_words(word_list: List<String>):  MutableMap<String, MutableList<String>> {
    /*Function that generates a map with unique words from the word_list. Each key has a list of words which
    come after the key in the word_list */
    val unique_words = word_list.toSet() //Create a Set of all keywords
    var wordmap = mutableMapOf<String, MutableList<String>>()

    //Also add one key that's blank. This will be for the first word of a sentence.
    wordmap.put("",  mutableListOf<String>())
    //Generating the wordmap keys (empty)
    for (word in unique_words){
        wordmap.put(word, mutableListOf<String>())
    }





    //Manually input the first word in our word_list into the blank key.
    wordmap.getOrPut("", ::mutableListOf).add(word_list[0])


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

            //To get to this one line of code and find the correct order of functions I think I might have cried
            wordmap.getOrPut("", ::mutableListOf).add(word_list[index+1])

        } else{
            //All other words that come after a keyword are appended to their respective keys
            wordmap.getOrPut(word_list[index], ::mutableListOf).add(word_list[index+1])


        }
    }

    return wordmap

}

fun generate_sentence(next_words: MutableMap<String, MutableList<String>> ): String {

    /*Generates a single random Markov sentence from a map of words */

    //var means that it's a mutable variable. val is read-only. Amazing.
    var new_sentence = mutableListOf<String>()
    var key = ""
    var choice_range = next_words.getOrPut(key, ::mutableListOf)
    while (choice_range.count() != 0){
        new_sentence.add(choice_range[Random.nextInt(choice_range.count())])
        key = new_sentence[new_sentence.count()-1]
        choice_range = next_words.getOrPut(key, ::mutableListOf)
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
    var list = "hello world one world fullstop. whatzzap."
    var gah = generate_text(list, 2)
    println(gah)

    val words = mutableListOf("I am Sam.\n\nI am Sam.\nSam I am.", "Hippopotomonstrosesquipedaliophobia.")


    val map1 = build_next_words(build_word_list(words[0]))
    val map2 =  mapOf("" to listOf("I", "I", "Sam") ,"I" to listOf("am", "am", "am."), "am" to listOf("Sam.", "Sam."), "Sam." to listOf(""),"Sam" to listOf("I"), "am." to listOf(""))
    println(map1)
    println(map2)
    println(map1.equals(map2))
}