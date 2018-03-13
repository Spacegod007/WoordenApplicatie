package performancetest.woordenapplicatie.logic;

import org.junit.Before;
import org.junit.Test;
import woordenapplicatie.logic.IWoordenLogic;
import woordenapplicatie.logic.WoordenLogic;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IWoordenLogicPTest
{
    private static final Logger LOGGER = Logger.getLogger(IWoordenLogicPTest.class.getName());

    private static final int performanceTestTimes = 12;

    private IWoordenLogic logic;
    private String test10K;
    private String test1M;

    @Before
    public void setUp() throws Exception
    {
        logic = new WoordenLogic();

        test10K = getShortTestString(); //ten thousand
        test1M = getLongTestString(); //one million

//        test10K = generateString(10000); //ten thousand
//        test1M = generateString(1000000); //one million
    }

    private long getAverageTime(long[] times)
    {
        long returnable = 0;
        for (long time : times)
        {
            returnable += time;
        }
        return returnable / times.length;
    }

    @Test
    public void getSplitText() throws Exception
    {
        getSplitTextTest(test10K);
        getSplitTextTest(test1M);
    }

    @Test
    public void getListText() throws Exception
    {
        getSplitTextTest(test10K);
        getSplitTextTest(test1M);
    }

    @Test
    public void getUniqueWordCount() throws Exception
    {
        getUniqueWordCountTest(test10K);
        getUniqueWordCountTest(test1M);
    }

    @Test
    public void sortDescending() throws Exception
    {
        sortDescendingTest(test10K);
        sortDescendingTest(test1M);
    }

    @Test
    public void frequenceOfWords() throws Exception
    {
        frequenceOfWordsTest(test10K);
        frequenceOfWordsTest(test1M);
    }

    @Test
    public void wordsOnLines() throws Exception
    {
        wordsOnLinesTest(test10K);
        wordsOnLinesTest(test1M);
    }

    /**
     * Tests getSplitText method a specified number of times
     * @param text used to test
     * @throws Exception if something goes wrong
     */
    private void getSplitTextTest(String text) throws Exception
    {
        long[] testTimes = new long[performanceTestTimes];
        for (int i = 0; i < performanceTestTimes; i++)
        {
            long beginTime = System.nanoTime();
            logic.getSplitText(text);
            testTimes[i] = System.nanoTime() - beginTime;
        }
        long resultTime = getAverageTime(testTimes);
        String logMessage = String.format("getSplitText - length: %s - Time measured: %s nanoseconds", text.length(), resultTime);
        LOGGER.log(Level.INFO, logMessage);
    }

    /**
     * Tests getListText method a specified number of times
     * @param text used to test
     * @throws Exception if something goes wrong
     */
    private void getListTextTest(String text) throws Exception
    {
        long[] testTimes = new long[performanceTestTimes];
        for (int i = 0; i < performanceTestTimes; i++)
        {
            long beginTime = System.nanoTime();
            logic.getListText(text);
            testTimes[i] = System.nanoTime() - beginTime;
        }
        long resultTime = getAverageTime(testTimes);
        String logMessage = String.format("getListText - length: %s - Time measured: %s nanoseconds", text.length(), resultTime);
        LOGGER.log(Level.INFO, logMessage);
    }

    /**
     * Tests getUniqueWordCount method a specified number of times
     * @param text used to test
     * @throws Exception if something goes wrong
     */
    private void getUniqueWordCountTest(String text) throws Exception
    {
        long[] testTimes = new long[performanceTestTimes];
        for (int i = 0; i < performanceTestTimes; i++)
        {
            long beginTime = System.nanoTime();
            logic.getUniqueWordCount(text);
            testTimes[i] = System.nanoTime() - beginTime;
        }
        long resultTime = getAverageTime(testTimes);
        String logMessage = String.format("getUniqueWordCount - length: %s - Time measured: %s nanoseconds", text.length(), resultTime);
        LOGGER.log(Level.INFO, logMessage);
    }

    /**
     * Tests sortDescending method a specified number of times
     * @param text used to test
     * @throws Exception if something goes wrong
     */
    private void sortDescendingTest(String text) throws Exception
    {
        long[] testTime = new long[performanceTestTimes];
        for (int i = 0; i < performanceTestTimes; i++)
        {
            long beginTime = System.nanoTime();
            logic.sortDescending(text);
            testTime[i] = System.nanoTime() - beginTime;
        }
        long resultTime = getAverageTime(testTime);
        String logMessage = String.format("sortDescending - length: %s - Time measured: %s nanoseconds", text.length(), resultTime);
        LOGGER.log(Level.INFO, logMessage);
    }

    /**
     * Tests frequenceOfWords method a specified number of times
     * @param text used to test
     * @throws Exception if something goes wrong
     */
    private void frequenceOfWordsTest(String text) throws Exception
    {
        long[] testTime = new long[performanceTestTimes];
        for (int i = 0; i < performanceTestTimes; i++)
        {
            long beginTime = System.nanoTime();
            logic.frequenceOfWords(text);
            testTime[i] = System.nanoTime() - beginTime;
        }
        long resultTime = getAverageTime(testTime);
        String logMessage = String.format("frequenceOfWords - length: %s - Time measured: %s nanoseconds", text.length(), resultTime);
        LOGGER.log(Level.INFO, logMessage);
    }

    /**
     * Tests wordsOnLines method a specified number of times
     * @param text used to test
     * @throws Exception if something goes wrong
     */
    private void wordsOnLinesTest(String text) throws Exception
    {
        long[] testTime = new long[performanceTestTimes];
        for (int i = 0; i < performanceTestTimes; i++)
        {
            long beginTime = System.nanoTime();
            logic.wordsOnLines(text);
            testTime[i] = System.nanoTime() - beginTime;
        }
        long resultTime = getAverageTime(testTime);
        String logMessage = String.format("wordsOnLines - Time measured: %d nanoseconds", resultTime);
        LOGGER.log(Level.INFO, logMessage);
    }

    /**
     * Generates a test string of random numbers
     * @param numberOfWords number of words to add to the test
     * @return a test string
     */
    private String generateString(int numberOfWords)
    {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < numberOfWords; i++)
        {
            builder.append(" ").append(random.nextInt());

            if ((i % 5) == 0)
            {
                builder.append("\n");
            }
        }

        return builder.toString();
    }

    private static final String EEN_TWEE_DRIE_VIER = "Een, twee, drie, vier\n";
    private static final String HOEDJE_VAN_PAPIER = "Hoedje van papier\n";

    private static final String DEFAULT_TEXT =   EEN_TWEE_DRIE_VIER +
            "Hoedje van, hoedje van\n" +
            EEN_TWEE_DRIE_VIER +
            HOEDJE_VAN_PAPIER +
            "\n" +
            "Heb je dan geen hoedje meer\n" +
            "Maak er één van bordpapier\n" +
            "Eén, twee, drie, vier\n" +
            HOEDJE_VAN_PAPIER +
            "\n" +
            EEN_TWEE_DRIE_VIER +
            "Hoedje van, hoedje van\n" +
            EEN_TWEE_DRIE_VIER +
            HOEDJE_VAN_PAPIER +
            "\n" +
            "En als het hoedje dan niet past\n" +
            "Zetten we 't in de glazenkas\n" +
            EEN_TWEE_DRIE_VIER +
            "Hoedje van papier";

    /**
     * Gets a test text of 10.064 words
     * @return a string containing 10.064 words
     */
    private String getShortTestString()
    {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < 148; i++)
        {
            builder.append(DEFAULT_TEXT).append(System.lineSeparator()).append(System.lineSeparator());
        }

        return builder.toString();
    }

    /**
     * Gets a test text of 1.000.008 words
     * @return a string containing 1.000.008 words
     */
    private String getLongTestString()
    {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < 14706; i++)
        {
            builder.append(DEFAULT_TEXT).append(System.lineSeparator()).append(System.lineSeparator());
        }

        return builder.toString();
    }
}
