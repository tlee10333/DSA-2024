import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

//Unit Test Cases taken from Software Design's unit test cases. This just checks to see if the word maps were correctly generated since there's no way to test the sentence generation since it's random.
class MainKtTest{

    //All the unit test words
    val words = mutableListOf(
        "I am Sam.\n\nI am Sam.\nSam I am.",
        "Hippopotomonstrosesquipedaliophobia.",
        "\nWait, is this the first line?\n",
        )

    @Test
    fun word0() {

        //Basic
        val expected: Map<String, List<String>> = mapOf(
            "" to listOf("I", "I", "Sam") ,
            "I" to listOf("am", "am", "am."),
            "am" to listOf("Sam.", "Sam."),
            "Sam." to listOf(""),
            "Sam" to listOf("I"),
            "am." to listOf("")
                )
        assertEquals(expected.toString(), build_next_words(build_word_list(words[0])).toString());
    }



    @Test
    fun word1(){

        val expected: Map<String, List<String>> = mutableMapOf(

                "" to  listOf("Hippopotomonstrosesquipedaliophobia."),
                "Hippopotomonstrosesquipedaliophobia." to listOf(""),

        )

        assertEquals(expected.toString(), build_next_words(build_word_list(words[1])).toString());

    }

    @Test
    fun word2(){

        val expected: Map<String, List<String>> = mutableMapOf(
            "" to listOf("Wait,"),
        "Wait," to  listOf("is"),
        "is" to  listOf("this"),
        "this" to listOf("the"),
        "the" to  listOf("first"),
        "first" to  listOf("line?"),
        "line?" to  listOf("")
            )

        assertEquals(expected.toString(), build_next_words(build_word_list(words[2])).toString());

    }
}