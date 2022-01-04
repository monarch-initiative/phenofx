module phenofx.questionnaire {
    exports org.monarchinitiative.phenofx.questionnnaire.phenoitem;
    exports org.monarchinitiative.phenofx.questionnnaire;

    requires transitive phenol.core; // due to Ontology property of `SelectableOntologyTree`
    requires org.slf4j;

    requires transitive javafx.controls;
    requires org.controlsfx.controls;
}