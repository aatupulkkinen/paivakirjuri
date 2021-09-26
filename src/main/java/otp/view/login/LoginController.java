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

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

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

    ValidationSupport validationSupport = new ValidationSupport();

    public void login(ActionEvent actionEvent) {
        String pass = password.getText();
        String name = username.getText();

        if (name.isBlank()) {
            validationSupport.registerValidator(username,
                    Validator.createRegexValidator("Virhe", "^.{1,32}$", Severity.WARNING)
            );
        }
        if (pass.isBlank()) {
            validationSupport.registerValidator(password,
                    Validator.createRegexValidator("Virhe", "^.{1,32}$", Severity.WARNING)
            );
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

