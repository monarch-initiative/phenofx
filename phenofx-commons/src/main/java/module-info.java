module phenofx.commons {
    exports org.monarchinitiative.phenofx.commons;

    requires javafx.controls;
    requires javafx.fxml;
    requires org.slf4j;

    opens org.monarchinitiative.phenofx.commons to javafx.fxml;
}