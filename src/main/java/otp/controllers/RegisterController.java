package otp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import otp.Main;
import otp.SceneController;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    public void backClicked() {
        openLoginScene();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void openLoginScene() {
        try {
            SceneController sc = Main.getSceneController();
            if (sc == null) return;
            sc.openLoginScene();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
