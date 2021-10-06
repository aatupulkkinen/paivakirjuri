package otp.controllers;

import javafx.scene.control.Button;
import javafx.scene.text.Text;
import org.controlsfx.control.textfield.CustomTextField;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest extends AutomaticRobotTest {

    @Override
    public void init() throws Exception {
        resourceName = "login.fxml";
        super.init();
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