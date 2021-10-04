package otp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
<<<<<<< HEAD
=======
import org.controlsfx.control.textfield.CustomTextField;
>>>>>>> 5f49781810a907b052ce864643201a96ffa71562
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import otp.Main;
import otp.model.daos.UserLocal;
import otp.model.entities.User;
import otp.model.daos.UserDao;
import otp.SceneController;
import otp.model.daos.UserDaoImpl;

import java.net.URL;
import java.util.ResourceBundle;

import static otp.model.db.DatabaseConstants.MAX_USER_NAME_LENGTH;
import static otp.model.db.DatabaseConstants.MAX_USER_PASSWORD_LENGTH;

public class LoginController implements Initializable {

    public Button recoverButton;

    public LoginController() {
        loginCRUD = new UserDaoImpl();
        userLocalRepo = new UserLocal();
    }

    private final UserDao loginCRUD;
    private final UserDao userLocalRepo;

    @FXML
    private TextField password;

    @FXML
    private TextFlow forgotPassword;

    public void forgotPassword(ActionEvent actionEvent) {
        openForgotPasswordScene();
    }

    @FXML
    private Button loginButton;

    @FXML
    private TextField username;

    @FXML
    private Text register;

    @FXML
    private Text incorrectData;

    @FXML
    private TextFlow registerText;

    final ValidationSupport validationSupport = new ValidationSupport();

    public void login(ActionEvent actionEvent) {
        String pass = password.getText();
        String name = username.getText();

        if (name.isBlank() || name.length() > MAX_USER_PASSWORD_LENGTH) {
            validationSupport.registerValidator(username,
                    Validator.createRegexValidator("Virhe",
                            String.format("^.{1,%d}$", MAX_USER_NAME_LENGTH), Severity.WARNING)
            );
        }
        if (pass.isBlank() || name.length() > MAX_USER_PASSWORD_LENGTH) {
            validationSupport.registerValidator(password,
                    Validator.createRegexValidator("Virhe",
                            String.format("^.{1,%d}$", MAX_USER_PASSWORD_LENGTH), Severity.WARNING)
            );
        }
        validationSupport.revalidate();

        User user = loginCRUD.get(name, pass);
        if (user == null) {
            showIncorrectData();
        } else {
            if (userLocalRepo.insert(name, pass)) {
                openMainScene();
            } else {
                // todo dialogi
            }
        }
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

<<<<<<< HEAD
    public void register() {
        try {

        } catch (){

        }
    }
=======
    private void openForgotPasswordScene() {
        try {
            SceneController sc = Main.getSceneController();
            if (sc == null) return;
            sc.openForgotPasswordScene();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

>>>>>>> 5f49781810a907b052ce864643201a96ffa71562
    private void showIncorrectData() {
        incorrectData.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

