module phenofx.ontology {
    exports org.monarchinitiative.phenofx.ontology.tree;
    exports org.monarchinitiative.phenofx.ontology.model;

    requires phenol.core;
    requires org.slf4j;

    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.base;
    requires org.controlsfx.controls;

    opens org.monarchinitiative.phenofx.ontology.tree to javafx.fxml;
}