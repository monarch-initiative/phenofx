module phenofx.demo {
    exports org.monarchinitiative.phenofx.demo to javafx.graphics;

    requires phenofx.ontology;

    requires spring.boot;
    requires spring.context;
    requires spring.boot.autoconfigure;

    requires javafx.fxml;
    requires org.slf4j;

    opens org.monarchinitiative.phenofx.demo;
    opens org.monarchinitiative.phenofx.demo.controller to javafx.fxml, spring.beans;
}