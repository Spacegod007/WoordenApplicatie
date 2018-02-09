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
     * Sorts the text descending in a list
     * @param text to be sorted
     * @return a List containing words sorted descending
     */
    List<String> sortDescending(String text);

    SortedSet<Map.Entry<String, Integer>> frequenceOfWords(String text);

    Map<String, Set<Integer>> wordsOnLines(String text);
}
