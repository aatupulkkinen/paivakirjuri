package otp.controllers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;
import otp.Main;
import otp.SceneController;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

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
    @FXML
    private Text quoteText;


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

    private String quote = null;

    public String fetchQuote() throws IOException {
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
        quoteText.setText(quote);
        quoteText.setVisible(true);

        return quote;
    }
}

