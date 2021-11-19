package org.monarchinitiative.phenofx.ontology.tree;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.monarchinitiative.phenol.io.OntologyLoader;
import org.monarchinitiative.phenol.ontology.data.Ontology;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Note, that the following must be added to VM options to be able to run this test:
 * --add-exports javafx.graphics/com.sun.javafx.application=ALL-UNNAMED
 * Otherwise, you will get an exception:
 * class org.testfx.toolkit.impl.ToolkitServiceImpl (in unnamed module @0x11e21d0e) cannot access class
 * com.sun.javafx.application.ParametersImpl (in module javafx.graphics) because module
 * javafx.graphics does not export com.sun.javafx.application to unnamed module @0x11e21d0e
 */
@ExtendWith(ApplicationExtension.class)
public class SelectableOntologyTreeTest {

    // TODO - replace with real ontology
    public static final File LOCAL_ONTOLOGY_OBO = new File("/home/ielis/tmp/hp.obo");

    private static Ontology ONTOLOGY = null;

    @BeforeAll
    public static void beforeAll() {
        ONTOLOGY = OntologyLoader.loadOntology(LOCAL_ONTOLOGY_OBO);
    }

    private SelectableOntologyTree tree;

    @Start
    public void start(Stage stage) throws Exception {
        tree = new SelectableOntologyTree();
        Scene scene = new Scene(tree, 600, 800);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void test(FxRobot robot) {
        Platform.runLater(() -> tree.ontologyProperty().set(ONTOLOGY));
        robot.sleep(20, TimeUnit.SECONDS);
    }
}