package otp.controllers;

import javafx.scene.text.Text;
import org.controlsfx.control.textfield.CustomTextField;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterControllerTest extends AutomaticRobotTest {

    @Test
    public void viewsPresent() {
        CustomTextField fName = lookup("#fName").query();
        assertEquals(fName.getPromptText(), "Etunimi");

        CustomTextField lName = lookup("#lName").query();
        assertEquals(lName.getPromptText(), "Sukunimi");

        CustomTextField userName = lookup("#userName").query();
        assertEquals(userName.getPromptText(), "Käyttäjänimi");

        CustomTextField passWord = lookup("#passWord").query();
        assertEquals(passWord.getPromptText(), "Salasana");

        CustomTextField confirmPassword = lookup("#confirmPassword").query();
        assertEquals(confirmPassword.getPromptText(), "Vahvista salasana");
    }

    @Test
    public void passwordsNotMatching() {
        Text incorrectData = lookup("#incorrectPassword").query();
        assertFalse(incorrectData.isVisible());

        clickOn("#fName");
        write("junit");
        clickOn("#lName");
        write("test");
        clickOn("#userName");
        write("junit-test");
        clickOn("#passWord");
        write("pass");
        clickOn("#confirmPassword");
        write("pass1");
        clickOn("#registerButton");

        assertTrue(incorrectData.isVisible());
    }

    @Override
    protected String getScene() {
        return "register_account.fxml";
    }
}
