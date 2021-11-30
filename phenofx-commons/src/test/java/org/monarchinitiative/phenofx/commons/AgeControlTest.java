package org.monarchinitiative.phenofx.commons;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.concurrent.TimeUnit;

/**
 * Note, that the following must be added to VM options to be able to run this test:
 * --add-exports javafx.graphics/com.sun.javafx.application=ALL-UNNAMED
 * Otherwise, you will get an exception:
 * class org.testfx.toolkit.impl.ToolkitServiceImpl (in unnamed module @0x11e21d0e) cannot access class
 * com.sun.javafx.application.ParametersImpl (in module javafx.graphics) because module
 * javafx.graphics does not export com.sun.javafx.application to unnamed module @0x11e21d0e
 */
@Disabled("GUI test is disabled unless ran manually")
@ExtendWith(ApplicationExtension.class)
public class AgeControlTest {

    private AgeControl ageControl;

    @Start
    public void start(Stage stage) throws Exception {
        ageControl = new AgeControl();
        Scene scene = new Scene(ageControl, 500, 150);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void test(FxRobot robot) {
        robot.sleep(10, TimeUnit.SECONDS);
    }
}