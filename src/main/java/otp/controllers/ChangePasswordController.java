package otp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.w3c.dom.ls.LSOutput;
import otp.Main;
import otp.SceneController;
import otp.model.daos.UserDao;
import otp.model.daos.UserDaoImpl;
import otp.model.daos.UserLocal;
import otp.model.entities.User;

import java.net.URL;
import java.util.ResourceBundle;

import static otp.model.db.DatabaseConstants.*;

public class ChangePasswordController implements Initializable {

    private final UserDao userLocalRepo = new UserLocal();

    @FXML
    public CustomTextField oldPassword;

    @FXML
    public CustomTextField newPassword;

    @FXML
    private Button changePassword;

    @FXML
    private Text username;

    final private ValidationSupport validationSupport = new ValidationSupport();

    final private UserDao userRemote = new UserDaoImpl();

    final private UserDao userLocal = new UserLocal();

    private User user = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user = userLocalRepo.get("", "");
        username.setText(user.getName() == null ? "" : user.getName());
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
                || currentPassword.length() > MAX_USER_PASSWORD_LENGTH
                || currentPassword.length() < MIN_USER_PASSWORD_LENGTH) {
            validationSupport.registerValidator(oldPassword,
                    Validator.createRegexValidator("Virhe",
                            String.format("^.{3,%d}$", MAX_USER_NAME_LENGTH), Severity.WARNING)
            );
        }

        if (newPass.isBlank()
                || newPass.length() > MAX_USER_PASSWORD_LENGTH
                || newPass.length() < MIN_USER_PASSWORD_LENGTH
        ) {
            validationSupport.registerValidator(newPassword,
                    Validator.createRegexValidator("Virhe",
                            String.format("^.{3,%d}$", MAX_USER_PASSWORD_LENGTH), Severity.WARNING)
            );
        }
        validationSupport.revalidate();

        if (user == null) return;

        User oldUser = new User();

        oldUser.setPassword(user.getPassword());
        oldUser.setName(user.getName());

        userRemote.changePassword(user, newPass);
        userLocal.changePassword(oldUser, newPass);
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
