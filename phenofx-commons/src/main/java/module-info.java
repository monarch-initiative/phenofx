module phenofx.commons {
    exports org.monarchinitiative.phenofx.commons;

    requires javafx.controls;
    requires javafx.fxml;

    opens org.monarchinitiative.phenofx.commons to javafx.fxml;
}