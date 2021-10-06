package otp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;

public class Main extends Application implements SceneController {

    private static SceneController sceneController = null;
    private Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        sceneController = this;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Paevaekirja");
        stage.setScene(scene);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void openMainScene() throws IOException {
        if (scene == null) return;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("paanakyma.fxml"));
        Parent mainCallWindowFXML = loader.load();
        scene.setRoot(mainCallWindowFXML);
    }

    public void openChangePasswordScene() throws IOException {
        if (scene == null) return;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("change_password.fxml"));
        Parent mainCallWindowFXML = loader.load();
        scene.setRoot(mainCallWindowFXML);
    }

    @Override
    public void openForgotPasswordScene() throws IOException {
        if (scene == null) return;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("forgot_password.fxml"));
        Parent mainCallWindowFXML = loader.load();
        scene.setRoot(mainCallWindowFXML);
    }

    public void openRegisterAccountScene() throws IOException{
        if (scene == null) return;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("register_account.fxml"));
        Parent mainCallWindowFXML = loader.load();
        scene.setRoot(mainCallWindowFXML);
    }

    public void openLoginScene() throws IOException{
        if (scene == null) return;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent mainCallWindowFXML = loader.load();
        scene.setRoot(mainCallWindowFXML);
    }

    public void openSettingsScene() throws IOException{
        if (scene == null) return;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("settings.fxml"));
        Parent mainCallWindowFXML = loader.load();
        scene.setRoot(mainCallWindowFXML);
    }

    public static SceneController getSceneController() {
        return sceneController;
    }
}
