package otp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.text.Text;
import otp.Main;
import otp.SceneController;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class ForgotController implements Initializable {

    private String tmpPass;

    @FXML
    private Button copyButton;
    @FXML
    private Label newPassString;

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

    public void newPass() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 7;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        System.out.println(generatedString);
        tmpPass = generatedString;

        newPassString.setText("Uusi väliaikainen salasanasi: " + generatedString);
        copyButton.setDisable(false);
        copyButton.setOpacity(1);
    }

    public void copyToClipBoard() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(tmpPass);
        clipboard.setContent(content);
        System.out.println("Copied to clipboard");

    }
}
