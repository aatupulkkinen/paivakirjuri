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
import otp.Main;
import otp.SceneController;
import org.jasypt.util.text.BasicTextEncryptor;
import otp.model.daos.ForgotDao;
import otp.model.daos.ForgotDaoImpl;
import otp.model.daos.UserDao;
import otp.model.daos.UserDaoImpl;
import otp.model.encryption.EncryptionHandler;
import otp.model.entities.Code;
import otp.model.entities.User;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    private String recoveryCodeString;


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

    @FXML
    private Text incorrectPassword;

    private User userToRegister;

    private final UserDao userCRUD;

    public RegisterController() {
        userCRUD = new UserDaoImpl();
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

    public void register() {
        String firstName = fName.getText();
        String lastName = lName.getText();
        String usrName = userName.getText();
        String pWord = passWord.getText();
        String confirmPWord = confirmPassword.getText();

        userToRegister = new User(usrName, pWord, firstName, lastName);


        if (!pWord.equals(confirmPWord)) {
            showIncorrectPassword();
        } // kun kaikki ok
        else if (pWord.equals(confirmPWord)) {
            recoveryCode();
            pushToDB();
            showStage();
        }
    }

    public void showStage() {
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
        TextField recoveryCodeField = new TextField(recoveryCodeString);
        Text text = new Text("Käyttäjätunnus luotu.\n\n Otathan alla olevan koodin varmaan talteen.\n Tarvitset sitä mikäli unohdat salasanasi.");
        comp.getChildren().add(text);
        comp.getChildren().add(recoveryCodeField);
        comp.getChildren().add(button);

        Scene stageScene = new Scene(comp, 300, 150);
        newStage.setTitle("Palautuskoodi");
        comp.setAlignment(Pos.CENTER);
        recoveryCodeField.setAlignment(Pos.CENTER);
        text.setTextAlignment(TextAlignment.CENTER);
        comp.setSpacing(10);
        button.setText("Jatka");
        button.setOnAction(buttonHandler);
        recoveryCodeField.setMaxWidth(100);
        text.wrappingWidthProperty().bind(stageScene.widthProperty().subtract(15));

        newStage.setScene(stageScene);
        newStage.show();
    }

    private void recoveryCode() {
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();

        recoveryCodeString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private void showIncorrectPassword() {
        incorrectPassword.setVisible(true);
    }

    public void pushToDB() {
        //
        System.out.println(userToRegister);
        userCRUD.insert(userToRegister);
        final ForgotDao forgotDao = new ForgotDaoImpl();
        forgotDao.insert(new Code(userToRegister.getName(), recoveryCodeString));
    }
}