package otp.controllers;

import javafx.scene.control.Button;
import javafx.scene.text.Text;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import otp.util.UserUtils;
import otp.util.Utils;

import static org.junit.jupiter.api.Assertions.*;

class ChangePasswordControllerTest extends AutomaticRobotTest {

    @BeforeAll
    public static void createUser(){
        UserUtils.getInstance().getUser();
    }

    @Override
    public void init() throws Exception {
        resourceName = "change_password.fxml";
        super.init();
    }

    @Test
    public void viewsPresent() {
        Text infoMessage = lookup("#infoMessage").query();
        assertFalse(infoMessage.isVisible());

        Text newPasswordText = lookup("#newPasswordText").query();
        assertEquals(newPasswordText.getText(), "Uusi salasana");

        Text password = lookup("#password").query();
        assertEquals(password.getText(), "Salasana");

        Button changePassword = lookup("#changePassword").query();
        assertEquals(changePassword.getText(), "Vaihda salasana");
    }

    @Test
    public void incorrectOldPassword() {
        Text incorrectData = lookup("#infoMessage").query();
        assertFalse(incorrectData.isVisible());

        clickOn("#oldPassword");
        write(Utils.generateString());
        clickOn("#newPassword");
        write(Utils.generateString());
        clickOn("#changePassword");

        assertTrue(incorrectData.isVisible());
    }

    @Test
    public void correctChangePassword() {
        Text incorrectData = lookup("#infoMessage").query();

        clickOn("#oldPassword");
        write(UserUtils.getInstance().getUser().getPassword());
        clickOn("#newPassword");
        write(Utils.generateString());
        clickOn("#changePassword");

        assertTrue(incorrectData.isVisible());
    }
}