package woordenapplicatie.logic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class IWoordenLogicTest
{
    private final String TEST_TEXT_1 = "this is a test text";
    private final String TEST_TEXT_2 = "this is test text 2, and this is supposed to test more";

    private IWoordenLogic logic;

    @Before
    public void setUp() throws Exception
    {
        logic = new WoordenLogic();
    }

    @Test
    public void getSplitText() throws Exception
    {
        String[] result = logic.getSplitText(TEST_TEXT_1);

        String[] simpleResult = TEST_TEXT_1.split(" ");

        for (int i = 0; i < simpleResult.length; i++)
        {
            Assert.assertFalse("General split fails", !result[i].equals(simpleResult[i]));
        }

        result = logic.getSplitText(TEST_TEXT_2);
        String[] advancedResult = TEST_TEXT_2.split("[, \n]+");

        for (int i = 0; i < advancedResult.length; i++)
        {
            Assert.assertFalse("Split fails on other character than spaces", !result[i].equals(advancedResult[i]));
        }
    }

    @Test
    public void getListText() throws Exception
    {
        List<String> result = logic.getListText(TEST_TEXT_1);
        List<String> simpleResult = Arrays.asList(TEST_TEXT_1.split(" "));

        Assert.assertFalse("list does not contain equal amount of items (simple)", result.size() != simpleResult.size());
        Assert.assertFalse("the items it contains aren't correct (simple)", !result.containsAll(simpleResult));

        result = logic.getListText(TEST_TEXT_2);
        List<String> advancedResult = Arrays.asList(TEST_TEXT_2.split("[, \n]+"));

        Assert.assertFalse("List does not contain equal amount of items (advanced)", result.size() != advancedResult.size());
        Assert.assertFalse("The items it contains aren't correct (advanced)", !result.containsAll(advancedResult));
    }

    @Test
    public void getUniqueWordCount() throws Exception
    {
        int simpleResult = 5;

        Assert.assertEquals("Check on unique words fails in simple attempt", simpleResult, logic.getUniqueWordCount(TEST_TEXT_1));

        int advancedResult = 9;

        Assert.assertEquals("Check on unique words fails in advanced attempt", advancedResult, logic.getUniqueWordCount(TEST_TEXT_2));
    }

    @Test
    public void sortDescending() throws Exception
    {
        List<String> result = logic.sortDescending(TEST_TEXT_1);
        Set<String> simpleResult = new TreeSet<>(Arrays.asList(TEST_TEXT_1.split(" ")));

        Assert.assertFalse("Result does not contain the items it should contain (simple)", result.size() != simpleResult.size() || !result.containsAll(simpleResult));
        Assert.assertFalse("Result does not contain the correct order of items (simple)", !(result.get(0).equals("this") || result.get(1).equals("test") || result.get(2).equals("text") || result.get(3).equals("is") || result.get(4).equals("a")));

        result = logic.sortDescending(TEST_TEXT_2);
        Set<String> advancedResult = new TreeSet<>(Arrays.asList(TEST_TEXT_2.split("[, \n]+")));

        Assert.assertFalse("Result does not contain the items it should contain (advanced)", result.size() != advancedResult.size() || !result.containsAll(advancedResult));
        Assert.assertFalse("Result does not contain the correct order of items (advanced)",
                !(result.get(0).equals("to") || result.get(1).equals("this") || result.get(2).equals("text") || result.get(3).equals("test") || result.get(4).equals("supposed")
                || result.get(5).equals("more") || result.get(6).equals("is") || result.get(7).equals("and") || result.get(8).equals("2")));
    }

    @Test
    public void frequenceOfWords() throws Exception
    {
        SortedSet<Map.Entry<String, Integer>> result = logic.frequenceOfWords(TEST_TEXT_1);
        List<String> simpleResult = Arrays.asList(TEST_TEXT_1.split(" "));

        for (Map.Entry<String, Integer> stringIntegerEntry : result)
        {
            Assert.assertFalse("Result does not contain valid key", !simpleResult.contains(stringIntegerEntry.getKey()));
            Assert.assertFalse("Result value is not correct value", stringIntegerEntry.getValue() != 1);
        }

        result = logic.frequenceOfWords(TEST_TEXT_2);
        String testWord = "test";
        int testValue = 2;

        final boolean[] foundObject = {false};

        for (Map.Entry<String, Integer> resultObject : result)
        {
            if (resultObject.getKey().equals(testWord))
            {
                Assert.assertFalse("Result value is incorrect", resultObject.getValue() != testValue);

                foundObject[0] = true;
                break;
            }
        }

        Assert.assertFalse("Result object not found", !foundObject[0]);
    }

    @Test
    public void wordsOnLines() throws Exception
    {
        Map<String, Set<Integer>> result = logic.wordsOnLines(TEST_TEXT_1);

        String[] simpleResult = TEST_TEXT_1.toLowerCase().split("[, \n]+");

        for (String splitItem : simpleResult)
        {
            Set<Integer> integers = result.get(splitItem);

            Assert.assertFalse("Every word should contain at least 1 value", integers == null);

            integers.iterator().forEachRemaining(integer -> Assert.assertFalse("Incorrect line number has been given to words in a text with one line", integer != 1));
        }

        String lineTestText = "this is a test text \n" +
                "with text on multiple on lines \n" +
                "with double text";

        result = logic.wordsOnLines(lineTestText);

        Set<Integer> thisIntegers = result.get("this");
        Assert.assertFalse("Every item should appear at least once", thisIntegers.size() != 1);

        Set<Integer> onIntegers = result.get("on");
        Assert.assertFalse("Every integer can only appear once per word", onIntegers.size() > 1);

        Set<Integer> withIntegers = result.get("with");
        withIntegers.iterator().forEachRemaining(integer -> Assert.assertFalse("Incorrect integers assigned to 'with' value", !(integer == 2 || integer == 3)));
    }
}