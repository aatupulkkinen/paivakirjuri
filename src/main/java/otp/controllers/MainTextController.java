package otp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
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
import otp.model.daos.UserDao;
import otp.model.daos.UserLocal;
import otp.model.daos.mark.MarkDao;
import otp.model.daos.mark.MarkDaoImpl;
import otp.model.entities.Mark;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainTextController implements Initializable {

    private final MarkDao markDao;
    private final UserDao userDao;

    public Button searchButton;
    public Text dateText;
    public TextArea markContent;
    public SplitPane splitPane;
    public VBox innerContainer;
    public ImageView backButton;
    public MenuButton menu;
    private boolean isSideViewOpen = true;

    public MainTextController() {
        this.markDao = new MarkDaoImpl();
        this.userDao = new UserLocal();
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

    public void saveText(ActionEvent actionEvent) {
        String content = markContent.getText();
        if (content.isBlank()) return;
        //
    }

    public void openSideView(MouseEvent actionEvent) {
        if (isSideViewOpen) {
            splitPane.setDividerPosition(0, 0.25);
            innerContainer.setPrefWidth(900);
            backButton.setRotate(0);
        } else {
            splitPane.setDividerPosition(0, 0.0);
            innerContainer.setPrefWidth(1200);
            backButton.setRotate(180);
        }
        isSideViewOpen = !isSideViewOpen;
    }

    public void openMenu(MouseEvent actionEvent) {
        menu.fire();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String name = userDao.get("", "").getName();
        List<Mark> marks = markDao.getAll(name);
        System.out.println(marks);
    }
}

