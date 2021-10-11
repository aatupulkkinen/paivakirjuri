package otp.controllers;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SettingsControllerTest extends AutomaticRobotTest {

    @Override
    public void init() throws Exception {
        super.init();
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

    @Override
    protected String getScene() {
        return "settings.fxml";
    }
}