module phenofx.gui {
    requires phenofx.commons;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.slf4j;

    opens org.monarchinitiative.phenofx.gui to javafx.fxml, javafx.graphics;
}