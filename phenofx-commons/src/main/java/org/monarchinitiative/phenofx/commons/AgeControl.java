package org.monarchinitiative.phenofx.commons;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class AgeControl extends VBox {

    @FXML
    private Spinner<Integer> yearsSpinner;
    @FXML
    private Spinner<Integer> monthsSpinner;
    @FXML
    private Spinner<Integer> daysSpinner;

    public AgeControl() {
        try {
            FXMLLoader loader = new FXMLLoader(AgeControl.class.getResource("AgeControl.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void initialize() {
        yearsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 120));
        monthsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 11));
        daysSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 30));
    }
}
