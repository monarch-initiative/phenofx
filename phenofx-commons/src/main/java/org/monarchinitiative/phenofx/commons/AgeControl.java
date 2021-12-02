package org.monarchinitiative.phenofx.commons;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class AgeControl extends VBox {

    private static final Logger LOGGER = LoggerFactory.getLogger(AgeControl.class);

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
        LOGGER.info("Initializing AgeControl");
        yearsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 120));
        monthsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 11));
        daysSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 30));
    }

    public ReadOnlyObjectProperty<Integer> yearsProperty() {
        return yearsSpinner.valueProperty();
    }

    public ReadOnlyObjectProperty<Integer> monthsProperty() {
        return monthsSpinner.valueProperty();
    }

    public ReadOnlyObjectProperty<Integer> daysProperty() {
        return daysSpinner.valueProperty();
    }
}
