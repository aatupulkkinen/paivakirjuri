package otp.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import otp.Main;
import otp.SceneController;

public class MainTextController {


    public Button searchButton;
    public Text dateText;
    public TextArea markContent;
    public SplitPane splitPane;
    public VBox innerContainer;
    public ImageView backButton;
    public MenuButton menu;
    private boolean isSideViewOpen = true;

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

    public void openSideView(MouseEvent actionEvent) {
        if (isSideViewOpen) {
            splitPane.setDividerPosition(0, 0.25);
            innerContainer.setPrefWidth(900);
            backButton.setRotate(180);
        } else {
            splitPane.setDividerPosition(0, 0.0);
            innerContainer.setPrefWidth(1200);
            backButton.setRotate(0);
        }
        isSideViewOpen = !isSideViewOpen;
    }

    public void openMenu(MouseEvent actionEvent) {
        menu.fire();
    }
}

