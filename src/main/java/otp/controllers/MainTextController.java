package otp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import otp.Main;
import otp.SceneController;

public class MainTextController {

    @FXML
    AnchorPane mainView = new AnchorPane();
    @FXML
    Button settingsButton = new Button();
    @FXML
    TextField searchField = new TextField();
    @FXML
    Button searchButton = new Button();
    @FXML
    Button sideViewButton = new Button();
    @FXML
    Button newMark = new Button();
    @FXML
    TextArea note = new TextArea();
    @FXML
    Button saveButton = new Button();
    @FXML
    Button emojiButton = new Button();
    @FXML
    Button colorButton = new Button();
    @FXML
    Text topicText = new Text();
    @FXML
    Text dateText = new Text();
    @FXML
    Button quoteButton = new Button();
    @FXML
    Button imageButton = new Button();



    private String text;



    public void saveText() {
        text = note.getText();
        System.out.println(text);
    }

    public String getText() {
        return this.text;
    }
    public void sideViewAction() {
        try {
            SceneController sc = Main.getSceneController();
            if (sc == null) return;
            //sc.openSideViewScene(); ei löydä openSideViewSceneä Mainistä
        } catch (Throwable t) {
            t.printStackTrace();
        }
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

}