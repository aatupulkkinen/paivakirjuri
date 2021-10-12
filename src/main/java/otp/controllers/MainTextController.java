package otp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import otp.Main;
import otp.SceneController;

import java.io.FileReader;

public class MainTextController {
    @FXML
    private TextArea writing = new TextArea();
    @FXML
    AnchorPane mainView = new AnchorPane(writing);
    @FXML
    private Button settings;
    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;
    @FXML
    private Button sidebar;
    @FXML
    private Button newMark;
    @FXML
    private Button saveButton;


    Stage sideView = new Stage();
    // teksti tallennetaan instanssimuuttujaksi myöhempää käyttöä varten
    private String text;


    public void saveText(ActionEvent ae) {
        System.out.println(text);
    }

    // getTextillä voidaan viedä teksti muihin sceneihin
    public String getText() {
        return text;
    }


    public void saveText() {
        text = writing.getText();
        System.out.println(text);
    }

    public void sideViewAction() {

    }


    public void settingsAction() {
        try {
            SceneController sc = Main.getSceneController();
            if (sc == null) return;
            sc.openSettingsScene();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void fetchQuote() {
    }
}

