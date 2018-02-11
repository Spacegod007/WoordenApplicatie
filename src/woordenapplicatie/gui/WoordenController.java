package woordenapplicatie.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import woordenapplicatie.logic.IWoordenLogic;
import woordenapplicatie.logic.PerformanceTestIWoordenLogic;
import woordenapplicatie.logic.WoordenLogic;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author frankcoenen
 */
public class WoordenController implements Initializable
{
    private static final Logger LOGGER = Logger.getLogger(WoordenController.class.getName());

    private IWoordenLogic woordenLogic;

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

    @FXML
    private Button btAantal;
    @FXML
    private TextArea taInput;
    @FXML
    private Button btSorteer;
    @FXML
    private Button btFrequentie;
    @FXML
    private Button btConcordantie;
    @FXML
    private Button btSet10000;
    @FXML
    private Button btSet1000000;
    @FXML
    private TextArea taOutput;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        woordenLogic = new PerformanceTestIWoordenLogic(new WoordenLogic());
        taInput.setText(DEFAULT_TEXT);
    }
    
    @FXML
    private void aantalAction(ActionEvent event) {
        String inputText = taInput.getText();
        taOutput.setText(String.format("Totaal aantal woorden: %d%sAantal verschillende woorden: %d", woordenLogic.getSplitText(inputText).length, String.format("%n"), woordenLogic.getUniqueWordCount(inputText)));
    }

    @FXML
    private void sorteerAction(ActionEvent event) {
        StringBuilder builder = new StringBuilder();
        woordenLogic.sortDescending(taInput.getText()).forEach(word -> builder.append(word).append(System.lineSeparator()));
        taOutput.setText(builder.toString());
    }

    @FXML
    private void frequentieAction(ActionEvent event) {
        StringBuilder builder = new StringBuilder();
        woordenLogic.frequenceOfWords(taInput.getText()).forEach(stringIntegerEntry -> builder.append(stringIntegerEntry.getKey()).append(" ").append(stringIntegerEntry.getValue()).append(System.lineSeparator()));
        taOutput.setText(builder.toString());
    }

    @FXML
    private void concordatieAction(ActionEvent event) {
        StringBuilder builder = new StringBuilder();
        woordenLogic.wordsOnLines(taInput.getText()).forEach((string, integers) -> builder.append(string).append(" ").append(Arrays.toString(integers.toArray())).append(String.format("%n")));
        taOutput.setText(builder.toString());
    }

    @FXML
    public void setTenThousand(ActionEvent actionEvent)
    {
        taInput.setText(getShortTestString());
    }

    @FXML
    public void setOneMillion(ActionEvent actionEvent)
    {
        taInput.setText(getLongTestString());
    }
}
