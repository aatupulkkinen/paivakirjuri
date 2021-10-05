package otp.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SettingsControllerTest extends ApplicationTest {

    private SettingsController controller;

    @BeforeAll
    public static void setupProperties() {
        System.setProperty("testfx.robot", "glass");
        System.setProperty("testfx.headless", "true");
        System.setProperty("prism.order", "sw");
        System.setProperty("prism.text", "t2k");
        System.setProperty("java.awt.headless", "true");
    }

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("/otp/settings.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        controller = fxmlLoader.getController();
        stage.show();
    }

    @Test
    public void viewsPresent() {
        Text username = lookup("#username").query();
        assertNotEquals(username, null);

        ImageView back = lookup("#back").query();
        assertNotEquals(back, null);

        Button login = lookup("#changePassword").query();
        assertEquals(login.getText(), "Vaihda salasana");
    }

}