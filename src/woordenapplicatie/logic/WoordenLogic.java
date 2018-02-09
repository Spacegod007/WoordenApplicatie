package woordenapplicatie.logic;

import java.util.*;

public class WoordenLogic implements IWoordenLogic
{
    @Override
    public String[] getSplitText(String text)
    {
        return text.toLowerCase().split("[, \n]+");
    }

    @Override
    public List<String> getListText(String text)
    {
        return Arrays.asList(getSplitText(text));
    }

    @Override
    public int getUniqueWordCount(String text)
    {
        return getHashSetText(text).size();
    }

    private HashSet<String> getHashSetText(String text)
    {
        return new HashSet<>(getListText(text));
    }

    private TreeSet<String> getTreeSetText(String text)
    {
        return new TreeSet<>(getListText(text));
    }

    @Override
    public List<String> sortDescending(String text)
    {
        List<String> strings = new LinkedList<>();
        strings.addAll(getTreeSetText(text).descendingSet());
        return strings;
    }

    @Override
    public SortedSet<Map.Entry<String, Integer>> frequenceOfWords(String text)
    {
        SortedSet<Map.Entry<String, Integer>> sortedSet = new TreeSet<>((e1, e2) ->
        {
            int res = e1.getValue().compareTo(e2.getValue());
            return res != 0 ? res : 1;
        });

        List<String> listText = getListText(text);
        HashSet<String> hashSetText = new HashSet<>(listText);

        hashSetText.forEach(string -> sortedSet.add(new AbstractMap.SimpleEntry<>(string, Collections.frequency(listText, string))));

        return sortedSet;
    }

    @Override
    public Map<String, Set<Integer>> wordsOnLines(String text)
    {
        String[] split = text.toLowerCase().split("[\n]+");
        List<String> list = Arrays.asList(split);
        Map<String, Set<Integer>> treeMap = new TreeMap<>();
        getHashSetText(text).forEach(string -> {
            Set<Integer> integers = new HashSet<>();

            for (int i = 0; i < list.size(); i++)
            {
                for (String word : list.get(i).split("[, ]"))
                {
                    if (word.contentEquals(string))
                    {
                        integers.add(i + 1);
                    }
                }

//                todo find regex so only separated words are allowed (improves performance by a factor of at least 4), remove workaround when completed
//                if (list.get(i).matches("\\b" + string + "\\b"))
//                {
//                    integers.add(i + 1);
//                }
            }
            treeMap.put(string, integers);
        });
        return treeMap;
    }
}
