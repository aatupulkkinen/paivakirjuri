package otp.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.CustomTextField;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

// --add-modules=javafx.graphics,javafx.fxml --add-reads javafx.graphics=ALL-UNNAMED --add-opens javafx.controls/com.sun.javafx.charts=ALL-UNNAMED --add-opens javafx.graphics/com.sun.javafx.iio=ALL-UNNAMED --add-opens javafx.graphics/com.sun.javafx.application=ALL-UNNAMED --add-opens javafx.graphics/com.sun.javafx.iio.common=ALL-UNNAMED --add-opens javafx.graphics/com.sun.javafx.css=ALL-UNNAMED --add-opens javafx.base/com.sun.javafx.runtime=ALL-UNNAMED
class LoginControllerTest extends ApplicationTest {
    private LoginController controller;

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("/otp/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        controller = fxmlLoader.getController();
        stage.show();
    }

    @Test
    public void viewsPresent() {
        CustomTextField username = lookup("#username").query();
        assertEquals(username.getPromptText(), "Käyttäjätunnus");

        CustomTextField password = lookup("#password").query();
        assertEquals(password.getPromptText(), "Salasana");

        Button login = lookup("#loginButton").query();
        assertEquals(login.getText(), "Kirjaudu");
    }

    @Test
    public void incorrectUsername() {
        Text incorrectData = lookup("#incorrectData").query();
        assertFalse(incorrectData.isVisible());

        clickOn("#username");
        write("rus");
        clickOn("#password");
        write("1234");
        clickOn("#loginButton");

        assertTrue(incorrectData.isVisible());
    }
}