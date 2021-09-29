package otp.view.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import otp.data.UsersEntity;
import otp.data.model.UserDao;

import java.net.URL;
import java.util.ResourceBundle;

import static otp.data.db.DatabaseConstants.MAX_USER_NAME_LENGTH;
import static otp.data.db.DatabaseConstants.MAX_USER_PASSWORD_LENGTH;

public class LoginController implements Initializable {

    public LoginController() {
        loginCRUD = new LoginHelper();
    }

    private final UserDao loginCRUD;

    @FXML
    private TextField password;

    @FXML
    private Text forgotPassword;

    @FXML
    private Button loginButton;

    @FXML
    private TextField username;

    @FXML
    private Text register;

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

        loginCRUD.get(name, pass);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

