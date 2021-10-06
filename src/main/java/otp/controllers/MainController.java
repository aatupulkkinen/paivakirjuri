package otp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import otp.Main;
import otp.SceneController;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public void settingsClicked() {
        openSettingsScene();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
