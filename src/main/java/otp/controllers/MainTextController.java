package otp.controllers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Handler;

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
    public ListView marksList;
    public Text saveStatus;
    private boolean isSideViewOpen = true;
    @FXML
    private Text quoteText;
    private String username;
    private Mark selectedMark = null;
    private ObservableList<Mark> observableList = FXCollections.observableArrayList();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

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
        if (selectedMark == null) {
            Mark mark = new Mark();
            mark.setCreated(new Date(System.currentTimeMillis()));
            mark.setName(username);
            mark.setColor("#101010");
            mark.setContent(content);
            boolean status = markDao.insert(mark);
            if (status) {
                observableList.add(mark);
                mark.setContent(content);
            }
            updateSaveStatus(status);

        } else {
            selectedMark.setModified(new Date(System.currentTimeMillis()));
            selectedMark.setContent(content);
            boolean status = markDao.update(selectedMark);
            updateSaveStatus(status);
        }
    }

    private void updateSaveStatus(boolean b) {
        saveStatus.setText(b ? "Success" : "Error");
        saveStatus.setVisible(true);
        if (b) {
            saveStatus.getStyleClass().remove("alert-danger");
            saveStatus.getStyleClass().add("alert-success");
        } else {
            saveStatus.getStyleClass().remove("alert-success");
            saveStatus.getStyleClass().add("alert-danger");
        }
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                saveStatus.setVisible(false);
            } catch (InterruptedException ignored) {
            }
        }).start();
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
        username = userDao.get("", "").getName();
        marksList.setCellFactory(param -> new ListCell<Mark>() {
            @Override
            protected void updateItem(Mark item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getContent() == null) {
                    setText(null);
                } else {
                    setText(item.getCreated().toString());
                }
            }
        });
        marksList.getSelectionModel().selectedItemProperty().addListener((ChangeListener<Mark>) (observable, oldValue, newValue) -> updateMark(newValue));
        marksList.setItems(observableList);
        fetchMarks();
        updateMark(null);
    }

    private void fetchMarks() {
        if (username == null) return;
        List<Mark> marks = markDao.getAll(username);
        observableList.addAll(marks);
        marksList.refresh();
    }

    private void updateMark(Mark mark) {
        selectedMark = mark;
        if (mark == null) {
            markContent.setText("");
            dateText.setText(LocalDate.now().format(formatter));
        } else {
            markContent.setText(mark.getContent());
            dateText.setText(mark.getCreated().toLocalDate().format(formatter));
        }
    }

    private String quote = null;

    public void fetchQuote() throws IOException {
        // connect to the api
        String quoteURL = "https://api.kanye.rest";
        URL url = new URL(quoteURL);
        URLConnection request = url.openConnection();
        request.connect();

        // print data
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject rootobj = root.getAsJsonObject();
        quote = rootobj.get("quote").getAsString();
        System.out.println(quote);
        quoteText.setText(quote);
        quoteText.setVisible(true);

        // return quote;
    }
}