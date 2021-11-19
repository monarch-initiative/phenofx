package org.monarchinitiative.phenofx.demo.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.monarchinitiative.phenofx.ontology.tree.SimpleOntologyTree;
import org.springframework.stereotype.Component;

@Component
public class Main {

    @FXML
    private Label notice;
    @FXML
    private SimpleOntologyTree simpleTree;

    @FXML
    private void initialize() {
    }

}
