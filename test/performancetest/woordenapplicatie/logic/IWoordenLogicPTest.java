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

    @Test
    public void getSplitText() throws Exception
    {
        long startTime = System.nanoTime();
        logic.getSplitText(test10K);
        long resultTime = System.nanoTime() - startTime;
        String logMessage = String.format("getSplitText - Time measured: %d nanoseconds", resultTime);
        LOGGER.log(Level.INFO, logMessage);

        startTime = System.nanoTime();
        logic.getSplitText(test1M);
        resultTime = System.nanoTime() - startTime;
        logMessage = String.format("getSplitText - Time measured: %d nanoseconds", resultTime);
        LOGGER.log(Level.INFO, logMessage);
    }

    @Test
    public void getListText() throws Exception
    {
        long startTime = System.nanoTime();
        logic.getListText(test10K);
        long resultTime = System.nanoTime() - startTime;
        String logMessage = String.format("getListText - Time measured: %d nanoseconds", resultTime);
        LOGGER.log(Level.INFO, logMessage);

        startTime = System.nanoTime();
        logic.getListText(test1M);
        resultTime = System.nanoTime() - startTime;
        logMessage = String.format("getListText - Time measured: %d nanoseconds", resultTime);
        LOGGER.log(Level.INFO, logMessage);
    }

    @Test
    public void getUniqueWordCount() throws Exception
    {
        long startTime = System.nanoTime();
        logic.getUniqueWordCount(test10K);
        long resultTime = System.nanoTime() - startTime;
        String logMessage = String.format("getUniqueWordCount - Time measured: %d nanoseconds", resultTime);
        LOGGER.log(Level.INFO, logMessage);

        startTime = System.nanoTime();
        logic.getUniqueWordCount(test1M);
        resultTime = System.nanoTime() - startTime;
        logMessage = String.format("getUniqueWordCount - Time measured: %d nanoseconds", resultTime);
        LOGGER.log(Level.INFO, logMessage);
    }

    @Test
    public void sortDescending() throws Exception
    {
        long startTime = System.nanoTime();
        logic.sortDescending(test10K);
        long resultTime = System.nanoTime() - startTime;
        String logMessage = String.format("sortDescending - Time measured: %d nanoseconds", resultTime);
        LOGGER.log(Level.INFO, logMessage);

        startTime = System.nanoTime();
        logic.sortDescending(test1M);
        resultTime = System.nanoTime() - startTime;
        logMessage = String.format("sortDescending - Time measured: %d nanoseconds", resultTime);
        LOGGER.log(Level.INFO, logMessage);
    }

    @Test
    public void frequenceOfWords() throws Exception
    {
        long startTime = System.nanoTime();
        logic.frequenceOfWords(test10K);
        long resultTime = System.nanoTime() - startTime;
        String logMessage = String.format("frequenceOfWords - Time measured: %d nanoseconds", resultTime);
        LOGGER.log(Level.INFO, logMessage);

        startTime = System.nanoTime();
        logic.frequenceOfWords(test1M);
        resultTime = System.nanoTime() - startTime;
        logMessage = String.format("frequenceOfWords - Time measured: %d nanoseconds", resultTime);
        LOGGER.log(Level.INFO, logMessage);
    }

    @Test
    public void wordsOnLines() throws Exception
    {
        long startTime = System.nanoTime();
        logic.wordsOnLines(test10K);
        long resultTime = System.nanoTime() - startTime;
        String logMessage = String.format("wordsOnLines - Time measured: %d nanoseconds", resultTime);
        LOGGER.log(Level.INFO, logMessage);

        startTime = System.nanoTime();
        logic.wordsOnLines(test1M);
        resultTime = System.nanoTime() - startTime;
        logMessage = String.format("wordsOnLines - Time measured: %d nanoseconds", resultTime);
        LOGGER.log(Level.INFO, logMessage);
    }

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
