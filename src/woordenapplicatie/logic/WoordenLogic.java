package woordenapplicatie.logic;

import java.util.*;

/**
 * An interpretation of logic of this application
 * Implementation of IWoordenLogic
 */
public class WoordenLogic implements IWoordenLogic
{
    @Override
    public String[] getSplitText(String text) // O(N^2)
    {
        return text.toLowerCase().split("[, \n]+"); // O(N^2)
    }

    @Override
    public List<String> getListText(String text) // O(N + N^2)
    {
        return Arrays.asList(getSplitText(text)); // O(1 + N^2)
    }

    @Override
    public int getUniqueWordCount(String text) // O(N + N^2+ 1)
    {
        return getHashSetText(text).size(); // O(1 + N^2+ 1)
    }

    /**
     * Gets a HashSet containing the individual words in the given text
     * @param text to be split into individual words
     * @return a HashSet containing the individual words in the given text
     */
    private HashSet<String> getHashSetText(String text) // O(2 + N^2)
    {
        return new HashSet<>(getListText(text)); // O(1 + 1 + N^2)
    }

    /**
     * Gets a TreeSet containing the individual words in the given text
     * @param text to be split into individual words
     * @return a TreeSet containing the individual words in the given text
     */
    private TreeSet<String> getTreeSetText(String text) // O(log(N) + 1 + N^2)
    {
        return new TreeSet<>(getListText(text)); // O(log(N) + 1 + N^2)
    }

    @Override
    public List<String> sortDescending(String text) // O(1 + N^2 + N)
    {
        List<String> strings = new LinkedList<>(); // O(1)
        strings.addAll(getTreeSetText(text).descendingSet()); // O(N^2 + N)
        return strings;
    }

    @Override
    public SortedSet<Map.Entry<String, Integer>> frequenceOfWords(String text)  // O(N^2 + N + N log(N) + 1)
    {
        List<String> listText = getListText(text); // O(N^2)
        HashSet<String> hashSetText = new HashSet<>(listText); // O (N)

        SortedSet<Map.Entry<String, Integer>> sortedSet = new TreeSet<>((e1, e2) ->
        {
            int res = e1.getValue().compareTo(e2.getValue());
            return res != 0 ? res : 1;
        }); // O(1)

        hashSetText.forEach(string -> sortedSet.add(new AbstractMap.SimpleEntry<>(string, Collections.frequency(listText, string)))); // O(N log(N))

        return sortedSet;
    }

    @Override
    public Map<String, Set<Integer>> wordsOnLines(String text) // O(1 + N^2 + N * (1 + 2)
    {
        Map<String, Set<Integer>> map = new HashMap<>(); // O(1)

        String[] lines = text.split("\n+"); // O(N^2)

        for (int i = 0; i < lines.length; i++) // O(N)
        {
            String line = lines[i]; // O(1)

            for (String word : line.split(" ")) // O(N^2 + N)
            {
                Set<Integer> integers = map.computeIfAbsent(word, k -> new HashSet<>()); // O(2)
                integers.add(i); // O(1)
            }
        }

        return map;
    }
}
