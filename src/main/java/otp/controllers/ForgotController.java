package otp.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.CustomTextField;
import org.jasypt.util.text.BasicTextEncryptor;
import org.jasypt.util.text.TextEncryptor;
import otp.Main;
import otp.SceneController;
import otp.model.daos.*;
import otp.model.encryption.EncryptionHandler;
import otp.model.entities.Code;
import otp.model.entities.User;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class ForgotController implements Initializable {

    final private UserDao userRemote = new UserDaoImpl();
    final private ForgotDao forgotDao = new ForgotDaoImpl();

    private String tmpPass;

    @FXML
    private CustomTextField username;

    @FXML
    private CustomTextField recoveryCode;

    @FXML
    public void backClicked() {
        openLoginScene();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void checkUsernameAndCode() {
        String usernameValue = username.getText();
        String recoveryCodeValue = recoveryCode.getText();

        Code code = forgotDao.get(usernameValue);
        if (code != null && code.getCode().equals(recoveryCodeValue)) {
            showStage(usernameValue);
        } else {
            // virheilmoitus tässä
        }
    }

    public void showStage(String usernmae) {
        Stage newStage = new Stage();
        VBox comp = new VBox();
        Button button = new Button();
        EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openLoginScene();
                newStage.close();
            }
        };
        newPass();
        TextField tempPassField = new TextField(tmpPass);
        if (!updatePassword(tmpPass, usernmae)){
            // virheilmoitus tässä
            return;
        }
        Text newPassText = new Text("Uusi väliaikainen salasanasi:");
        comp.getChildren().add(newPassText);
        comp.getChildren().add(tempPassField);

        Scene stageScene = new Scene(comp, 300, 150);
        newStage.setTitle("Salasanan palautus");
        comp.setAlignment(Pos.CENTER);
        tempPassField.setAlignment(Pos.CENTER);
        newPassText.setTextAlignment(TextAlignment.CENTER);
        comp.setSpacing(10);
        Text logInWithThisText = new Text("Voit nyt kirjautua sisään tällä salasanalla.");
        comp.getChildren().add(logInWithThisText);
        logInWithThisText.setTextAlignment(TextAlignment.CENTER);
        comp.getChildren().add(button);
        button.setText("Jatka");
        button.setOnAction(buttonHandler);
        tempPassField.setMaxWidth(100);
        newPassText.wrappingWidthProperty().bind(stageScene.widthProperty().subtract(15));
        logInWithThisText.wrappingWidthProperty().bind(stageScene.widthProperty().subtract(15));

        newStage.setScene(stageScene);
        newStage.show();
    }

    private boolean updatePassword(String tmpPass, String username) {
        User user = userRemote.get(username);
        if (user != null) {
            return userRemote.changePassword(user, tmpPass) != null;
        }
        return false;
    }

    public void newPass() {
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 7;
        Random random = new Random();

        tmpPass = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
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
