package org.monarchinitiative.phenofx.gui;

import javafx.fxml.FXML;
import org.monarchinitiative.phenofx.commons.AgeControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    @FXML
    private AgeControl ageControl;

    @FXML
    private void initialize() {
        LOGGER.info("Initializing the Main controller");
    }

    @FXML
    private void statusButtonAction() {
        LOGGER.info("Current status: {} years, {} months, and {} days",
                ageControl.yearsProperty().get(), ageControl.monthsProperty().get(), ageControl.daysProperty().get());
    }
}
