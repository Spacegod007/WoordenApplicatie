package woordenapplicatie.logic;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PerformanceTestIWoordenLogic implements IWoordenLogic
{
    private static final Logger LOGGER = Logger.getLogger(PerformanceTestIWoordenLogic.class.getName());

    private final IWoordenLogic logic;

    public PerformanceTestIWoordenLogic(IWoordenLogic logic)
    {
        this.logic = logic;
    }

    @Override
    public String[] getSplitText(String text)
    {
        long startTime = System.nanoTime();
        String[] returnable = logic.getSplitText(text);
        long resultTime = System.nanoTime() - startTime;
        String logMessage = String.format("getSplitText - Time measured: %d nanoseconds", resultTime);
        LOGGER.log(Level.INFO, logMessage);
        return returnable;
    }

    @Override
    public List<String> getListText(String text)
    {
        long startTime = System.nanoTime();
        List<String> returnable = logic.getListText(text);
        long resultTime = System.nanoTime() - startTime;
        String logMessage = String.format("getListText - Time measured: %d nanoseconds", resultTime);
        LOGGER.log(Level.INFO, logMessage);
        return returnable;
    }

    @Override
    public int getUniqueWordCount(String text)
    {
        long startTime = System.nanoTime();
        int returnable = logic.getUniqueWordCount(text);
        long resultTime = System.nanoTime() - startTime;
        String logMessage = String.format("getUniqueWordCount - Time measured: %d nanoseconds", resultTime);
        LOGGER.log(Level.INFO, logMessage);
        return returnable;
    }

    @Override
    public List<String> sortDescending(String text)
    {
        long startTime = System.nanoTime();
        List<String> returnable = logic.sortDescending(text);
        long resultTime = System.nanoTime() - startTime;
        String logMessage = String.format("sortDescending - Time measured: %d nanoseconds", resultTime);
        LOGGER.log(Level.INFO, logMessage);
        return returnable;
    }

    @Override
    public SortedSet<Map.Entry<String, Integer>> frequenceOfWords(String text)
    {
        long startTime = System.nanoTime();
        SortedSet<Map.Entry<String, Integer>> returnable = logic.frequenceOfWords(text);
        long resultTime = System.nanoTime() - startTime;
        String logMessage = String.format("frequenceOfWords - Time measured: %d nanoseconds", resultTime);
        LOGGER.log(Level.INFO, logMessage);
        return returnable;
    }

    @Override
    public Map<String, Set<Integer>> wordsOnLines(String text)
    {
        long startTime = System.nanoTime();
        Map<String, Set<Integer>> returnable = logic.wordsOnLines(text);
        long resultTime = System.nanoTime() - startTime;
        String logMessage = String.format("wordsOnLines - Time measured: %d nanoseconds", resultTime);
        LOGGER.log(Level.INFO, logMessage);
        return returnable;
    }
}
