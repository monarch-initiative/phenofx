package org.monarchinitiative.phenofx.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Run by:
 * <pre>
 *     cd phenofx
 *     ./mvnw clean package
 *     java --module-path phenofx-gui/target/phenofx-gui-0.0.1-SNAPSHOT.jar:phenofx-gui/target/lib -m phenofx.gui/org.monarchinitiative.phenofx.gui.PhenofxGui
 * </pre>
 */
public class PhenofxGui extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(PhenofxGui.class);

    @Override
    public void init() throws Exception {
        super.init();
        LOGGER.info("Initializing the GUI");
    }

    @Override
    public void start(Stage stage) throws Exception {
        LOGGER.info("About to load `Main.fxml`");
        FXMLLoader loader = new FXMLLoader(PhenofxGui.class.getResource("Main.fxml"));
        loader.setControllerFactory(clz -> new Main());
        Parent parent = loader.load();

        Scene scene = new Scene(parent, 400, 200);
        stage.setTitle("PhenoFX");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        LOGGER.info("Bye!");
    }

    public static void main(String[] args) {
        Application.launch(PhenofxGui.class, args);
    }
}
