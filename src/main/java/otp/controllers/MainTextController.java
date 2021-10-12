package otp.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import otp.Main;
import otp.SceneController;

public class MainTextController {


    public Button searchButton;
    public Text dateText;
    public TextArea markContent;

    public void settingsAction() {
        try {
            SceneController sc = Main.getSceneController();
            if (sc == null) return;
            sc.openSettingsScene();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void saveText(ActionEvent actionEvent) {

    }

    public void openSideView(ActionEvent actionEvent) {

    }

    public void openMenu(ActionEvent actionEvent) {

    }
}

