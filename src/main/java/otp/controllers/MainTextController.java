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
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import otp.Main;
import otp.SceneController;
import otp.model.daos.UserDao;
import otp.model.daos.UserLocal;
import otp.model.daos.image.ImageDao;
import otp.model.daos.image.ImageDaoImpl;
import otp.model.daos.mark.MarkDao;
import otp.model.daos.mark.MarkDaoImpl;
import otp.model.daos.quote.QuoteDao;
import otp.model.daos.quote.QuoteDaoImpl;
import otp.model.entities.Image;
import otp.model.entities.Mark;
import otp.model.entities.Quote;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.Buffer;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Handler;

public class MainTextController implements Initializable {

    private final MarkDao markDao;
    private final UserDao userDao;
    private final QuoteDao quoteDao;
    private final ImageDao imageDao;

    public Button searchButton;
    public Text dateText;
    public TextArea markContent;
    public SplitPane splitPane;
    public VBox innerContainer;
    public ImageView backButton;
    public MenuButton menu;
    public ListView marksList;
    public Text saveStatus;
    @FXML
    private ImageView markImage;
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
        this.quoteDao = new QuoteDaoImpl();
        this.imageDao = new ImageDaoImpl();
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
        boolean status = false;
        if (selectedMark == null) {
            Mark mark = new Mark();
            mark.setCreated(new Date(System.currentTimeMillis()));
            mark.setName(username);
            mark.setColor("#101010");
            mark.setContent(content);
            int id = markDao.insert(mark);
            if (id != -1) {
                mark.setId(id);
                status = true;
                observableList.add(mark);
            }
        } else {
            selectedMark.setModified(new Date(System.currentTimeMillis()));
            selectedMark.setContent(content);
            status = markDao.update(selectedMark);
        }
        updateSaveStatus(status);
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
        marksList.getSelectionModel().selectedItemProperty().addListener((ChangeListener<Mark>) (observable, oldValue, newValue) -> {
            updateMark(newValue);
        });
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
        new Thread(() -> {
            selectedMark = mark;
            if (mark == null) {
                markContent.setText("");
                dateText.setText(LocalDate.now().format(formatter));
                quoteText.setText("");
                markImage.setImage(null);
            } else {
                markContent.setText(mark.getContent());
                Quote quote = quoteDao.get(selectedMark.getId());
                quoteText.setText(quote == null ? "" : quote.getContent());
                dateText.setText(mark.getCreated().toLocalDate().format(formatter));
                Image image = imageDao.get(selectedMark.getId());
                if (image != null) {
                    markImage.setImage(new javafx.scene.image.Image(new ByteArrayInputStream(image.getContent())));
                } else {
                    markImage.setImage(null);
                }
            }
        }).start();
    }

    private String quote = null;

    public void fetchQuote() {
        new Thread(() -> {
            try {
                // connect to the api
                String quoteURL = "https://api.kanye.rest";
                URL url = null;
                url = new URL(quoteURL);
                URLConnection request = url.openConnection();
                request.connect();

                // print data
                JsonParser jp = new JsonParser();
                JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
                JsonObject rootobj = root.getAsJsonObject();
                quote = rootobj.get("quote").getAsString();
                // return quote;
                System.out.println(quote);
                quoteText.setText(quote);
                quoteText.setVisible(true);
                addQuoteToMark(quote);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void addQuoteToMark(String quote) {
        if (selectedMark == null) return;
        Quote Obj = new Quote();
        Obj.setMarkId(selectedMark.getId());
        Obj.setContent(quote);
        quoteDao.insert(Obj);
    }

    public void newNote(ActionEvent actionEvent) {
        updateMark(null);
    }

    public void addImage(ActionEvent actionEvent) {
        Stage stage = Main.getSceneController().getStage();
        Mark mark = selectedMark;
        if (stage == null || mark == null) return;

        final FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter ext1 = new FileChooser.ExtensionFilter("JPG files(*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter ext2 = new FileChooser.ExtensionFilter("PNG files(*.png)", "*.PNG");
        FileChooser.ExtensionFilter ext3 = new FileChooser.ExtensionFilter("JPG files(*.jpeg)", "*.jpeg");
        fileChooser.getExtensionFilters().addAll(ext1, ext2, ext3);
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            saveImage(file, selectedMark.getId());
        }
    }

    private void saveImage(File file, int markId) {
        Thread t = new Thread(() -> {
            byte[] bytes = new byte[(int) file.length()];
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                fileInputStream.read(bytes);
                fileInputStream.close();
                imageDao.insert(bytes, markId);
                markImage.setImage(new javafx.scene.image.Image(new ByteArrayInputStream(bytes)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}