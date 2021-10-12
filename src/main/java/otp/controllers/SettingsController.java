package otp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import otp.Main;
import otp.SceneController;
import otp.model.daos.UserDao;
import otp.model.daos.UserLocal;
import otp.model.encryption.EncryptionHandler;
import otp.model.entities.User;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    private final EncryptionHandler encryptionHandler = new EncryptionHandler();

    @FXML
    private Button changePassword;

    @FXML
    private ImageView back;

    @FXML
    private Text username;

    private final UserDao userLocalRepo = new UserLocal();

    public void changePassword(ActionEvent actionEvent) {
        openChangePasswordScene();
    }

    @FXML
    public void backClicked() {
        openMainScene();
    }

    private void openMainScene() {
        try {
            SceneController sc = Main.getSceneController();
            if (sc == null) return;
            sc.openMainScene();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private void openChangePasswordScene() {
        try {
            SceneController sc = Main.getSceneController();
            if (sc == null) return;
            sc.openChangePasswordScene();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User user = userLocalRepo.get("", "");
        String decryptedName = encryptionHandler.decrypt(user.getName());
        username.setText(user.getName() == null ? "" : decryptedName);
    }
}
