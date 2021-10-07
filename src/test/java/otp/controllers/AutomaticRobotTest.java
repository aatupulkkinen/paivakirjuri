package otp.controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.testfx.framework.junit5.ApplicationTest;

abstract public class AutomaticRobotTest extends ApplicationTest {

    protected Initializable controller;

    protected String resourceName = "";

    @Override
    public void start(Stage stage) throws Exception {
        if (resourceName.isBlank()) {
            throw new RuntimeException("virhe");
        }
        super.start(stage);

        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/otp/" + resourceName));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setScene(scene);
        controller = fxmlLoader.getController();
        stage.show();
    }

    @BeforeAll
    public static void setupProperties() {
        System.setProperty("testfx.robot", "glass");
        System.setProperty("testfx.headless", "true");
        System.setProperty("prism.order", "sw");
        System.setProperty("prism.text", "t2k");
        System.setProperty("java.awt.headless", "true");
    }
}
