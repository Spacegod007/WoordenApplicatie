package woordenapplicatie.logic;

import java.util.*;

/**
 * Facade class to execute the logic of the application
 */
public interface IWoordenLogic
{
    /**
     * Gets the text split into an array containing every word separated
     * @param text to be split
     * @return a String Array containing every word separated
     */
    String[] getSplitText(String text);

    /**
     * Gets the text split into a list containing every word separated
     * @param text to be split
     * @return a String List containing every word separated
     */
    List<String> getListText(String text);

    /**
     * Get the amount of unique words in the text
     * @param text to count the words in
     * @return the amount of unique words in the given text
     */
    int getUniqueWordCount(String text);

    /**
     * Sorts a text as a list of words alphabetically descending
     * @param text to sort
     * @return a list of Strings sorted alphabetically descending
     */
    List<String> sortDescending(String text);

    /**
     * Gets the frequency of every word in a text
     * @param text to get the frequency of
     * @return a SortedSet containing map entries with a String key (word in the text) and an Integer value (frequency)
     */
    SortedSet<Map.Entry<String, Integer>> frequenceOfWords(String text);

    /**
     * Gets the values on which lines what words are located
     * @param text to get the words of
     * @return a mapping containing a String key (word in the text) and a set of Integer values (lines the word is found on)
     */
    Map<String, Set<Integer>> wordsOnLines(String text);
}
