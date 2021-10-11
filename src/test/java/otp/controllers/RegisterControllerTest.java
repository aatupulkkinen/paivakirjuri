package otp.controllers;

import javafx.scene.text.Text;
import org.controlsfx.control.textfield.CustomTextField;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterControllerTest extends AutomaticRobotTest {

    @Override
    public void init() throws Exception {
        resourceName = "register_account.fxml";
        super.init();


    }
    RegisterController registerController = new RegisterController();
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

     @Test
    public void passwordEncryption() {
        registerController.setEncryptPass("junit-testi");
        String test1 = "ohtuprokkis";
        String test2 = "Opettajana Auvo";
        Boolean encrypted = true;
        String t1Encrypted = registerController.encrypt(test1);
        String t2Encrypted = registerController.encrypt(test2);
        if (test1.equals(t1Encrypted) || test2.equals(t2Encrypted) ) {
            encrypted = false;
        }
        assertTrue(encrypted);
     }
}
