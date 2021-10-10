package otp.controllers;

import org.controlsfx.control.textfield.CustomTextField;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterControllerTest extends AutomaticRobotTest {

    @Override
    public void init() throws Exception {
        resourceName = "register_account.fxml";
        super.init();
    }

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

    // @Test
    // public void passwordsNotMatching() {
    // }
}
