package otp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import org.controlsfx.control.textfield.CustomTextField;
import otp.Main;
import otp.SceneController;
import otp.model.daos.UserDao;
import otp.model.daos.UserDaoImpl;
import otp.model.daos.UserLocal;
import otp.model.entities.User;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static otp.model.db.DatabaseConstants.*;


public class ChangePasswordController implements Initializable {

    private final UserDao userLocalRepo = new UserLocal();

    @FXML
    public CustomTextField oldPassword;

    @FXML
    public CustomTextField newPassword;

    public Text infoMessage;

    @FXML
    private Button changePassword;

    @FXML
    private Text username;

    final private UserDao userRemote = new UserDaoImpl();

    final private UserDao userLocal = new UserLocal();

    private User user = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user = userLocalRepo.get("", "");
        if (user != null) {
            username.setText(user.getName() == null ? "" : user.getName());
        }
    }

    @FXML
    public void backClicked() {
        openSettingsScene();
    }

    @FXML
    void onChangePassword(ActionEvent actionEvent) {
        String currentPassword = oldPassword.getText();
        String newPass = newPassword.getText();

        if (currentPassword.isBlank()
                || currentPassword.length() < MIN_USER_PASSWORD_LENGTH
                || newPass.isBlank()
                || newPass.length() < MIN_USER_PASSWORD_LENGTH
        ) {
            updateInfoMessage(false,
                    String.format("Salasanasi on oltava vähintään %d merkkiä pitkä", MIN_USER_NAME_LENGTH)
            );
            return;
        }

        if (user == null) return;

        if (!Objects.equals(user.getPassword(), currentPassword) ||
                Objects.equals(user.getPassword(), newPass)
        ) {
            updateInfoMessage(false, "Virheellinen salasana");
            return;
        }

        User oldUser = new User();

        oldUser.setPassword(user.getPassword());
        oldUser.setName(user.getName());

        userRemote.changePassword(user, newPass);
        userLocal.changePassword(oldUser, newPass);
        updateInfoMessage(true, "Salasana on vaihdettu");
    }

    private void updateInfoMessage(boolean isValid, String message) {
        if (!infoMessage.isVisible()) infoMessage.setVisible(true);
        if (isValid) {
            infoMessage.getStyleClass().remove("alert-danger");
            infoMessage.getStyleClass().add("alert-success");
        } else {
            infoMessage.getStyleClass().remove("alert-success");
            infoMessage.getStyleClass().add("alert-danger");
        }
        infoMessage.setText(message);
    }

    private void openSettingsScene() {
        try {
            SceneController sc = Main.getSceneController();
            if (sc == null) return;
            sc.openSettingsScene();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
