module phenofx.ontology {
    exports org.monarchinitiative.phenofx.ontology.tree;
    exports org.monarchinitiative.phenofx.ontology.model;

    requires transitive phenol.core; // due to Ontology property of `SelectableOntologyTree`
    requires org.slf4j;

    requires transitive javafx.controls;
    requires org.controlsfx.controls;
}