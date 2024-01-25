"""
Use a Markov process to generate random sentences based on a source text.
"""

import random


def build_word_list(source_text):
    """
    splits a string source text into a list without
    whitespaces

    Args:
        source_text: a string which will be turned into a list

    Returns:
        a list form of source_text seperated by whitespaces.
    """
    return source_text.split()


def build_next_words(word_list):
    """
    Creates a dictionary to see which words follow which

    Args:
        word_list: list of words created by build_word_list
    Returns:
        a dictionary with each unique word and every word following that
    """
    dictionary = {"": [word_list[0]]}

    unique_words = list(set(word_list[0 : len(word_list) - 1]))

    # Idk for that one test don't look at me man (checking last word)
    if "." in word_list[-1] or "?" in word_list[-1] or "!" in word_list[-1]:
        unique_words.append(word_list[-1])

    # Drafting dictionary
    for key in unique_words:
        dictionary.update({key: []})

    # Creating empty dictionary with just the keys
    for index in range(0, len(word_list) - 1):
        if (
            "." in word_list[index][-1]
            or "?" in word_list[index][-1]
            or "!" in word_list[index][-1]
        ):
            dictionary[""].append(word_list[index + 1])

        else:
            dictionary[word_list[index]].append(word_list[index + 1])

    for key, value in dictionary.items():
        if not value:
            dictionary[key] = [""]

    return dictionary


def generate_sentence(next_words):
    """
    Generates random sentence given dictionary next_words

    Args:
        next_words: A dictionary formatted with keys having
        values of every word that follows the key, next_words
        is made from using the function build_next_words
    Return:
        a string randomly generated usng next_words
    """

    new_sentence = []

    key = ""
    choice_range = next_words[key]

    while choice_range != [""]:
        new_sentence.append(random.choice(choice_range))
        key = new_sentence[-1]
        choice_range = next_words[key]

    return " ".join(new_sentence)


def generate_text(next_words, num_sentences):
    """
    Generates full markov text

    Args:
        next_words: A dictionary formatted with keys having
        values of every word that follows the key, next_words
        is made from using the function build_next_words
        num_sentences: number of sentences in the text
    Returns:
       A string of markov text based on next_words"""
    text = []

    for _ in range(num_sentences):
        text.append(generate_sentence(next_words))

    return " ".join(text)
