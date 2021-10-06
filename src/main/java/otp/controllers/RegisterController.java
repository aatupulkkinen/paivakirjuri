package otp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import otp.Main;
import otp.SceneController;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    public void backClicked() {
        openLoginScene();
    }

    @FXML
    private TextField fName;

    @FXML
    private TextField lName;

    @FXML
    private TextField userName;

    @FXML
    private TextField passWord;

    @FXML
    private TextField confirmPassword;

    @FXML
    private Button registerButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    private void openLoginScene() {
        try {
            SceneController sc = Main.getSceneController();
            if (sc == null) return;
            sc.openLoginScene();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void register (){
        String firstName = fName.getText();
        String lastName = lName.getText();
        String usrName = userName.getText();
        String pWord = passWord.getText();
        String confirmPWord = confirmPassword.getText();

        System.out.println(firstName+" "+lastName+" "+usrName+" "+pWord+" "+confirmPWord);
    }

}
