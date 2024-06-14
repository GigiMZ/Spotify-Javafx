package com.example.spotify;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import io.github.cdimascio.dotenv.Dotenv;
import javafx.stage.Stage;

public class MainController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    @FXML
    private ImageView playpause;

    private static boolean play_pause;

    @FXML
    private ImageView rightskip;

    @FXML
    private ImageView leftskip;

    @FXML
    private ImageView volume;

    @FXML
    private ImageView avatar;

    @FXML
    private Text username;

    @FXML
    private Slider timeline;

    @FXML
    private Slider slider_volume;

    @FXML
    private Text name;

    @FXML
    private Text authors;

    @FXML
    private Text current;

    @FXML
    private Text total;

    @FXML
    protected HBox home;

    @FXML
    private HBox search;

    @FXML
    private BorderPane border;

    @FXML
    public void play_pause() {
        Dotenv dotenv = Dotenv.load();
        if (!play_pause) {
            Image image = new Image("file:///"+dotenv.get("DIRECTORY")+"Spotify/src/main/resources/com/example/spotify/pictures/709691.png");
            playpause.setImage(image);
            play_pause = true;
        } else {
            Image image = new Image("file:///"+dotenv.get("DIRECTORY")+"Spotify/src/main/resources/com/example/spotify/pictures/8029490.png");
            playpause.setImage(image);
            play_pause = false;
        }
    }

    @FXML
    public void volume_icon() {
        Dotenv dotenv = Dotenv.load();
        if (slider_volume.getValue() < 3) {

            Image image = new Image("file:///"+dotenv.get("DIRECTORY")+"Spotify/src/main/resources/com/example/spotify/pictures/mute.png");
            volume.setImage(image);
        } else if (slider_volume.getValue() < 50) {
            Image image = new Image("file:///"+dotenv.get("DIRECTORY")+"Spotify/src/main/resources/com/example/spotify/pictures/volume-down.png");
            volume.setImage(image);
        } else {
            Image image = new Image("file:///"+dotenv.get("DIRECTORY")+"Spotify/src/main/resources/com/example/spotify/pictures/volume-up.png");
            volume.setImage(image);
        }
    }

    @FXML
    public void profile() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("profile.fxml")));
        Stage window = (Stage) avatar.getScene().getWindow();
        window.setTitle("Profile");
        Scene scene = new Scene(root, 800, 560);
        window.setScene(scene);
        window.setMinHeight(608);
        window.setMinWidth(816);
        window.setMaxHeight(1080);
        window.setMaxWidth(1920);
        window.setScene(scene);
        window.show();
    }

    @FXML
    public void toSearch() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("search.fxml")));
        Stage window = (Stage) avatar.getScene().getWindow();
        window.setTitle("Search");
        Scene scene = new Scene(root, 800, 560);
        window.setScene(scene);
        window.setMinHeight(608);
        window.setMinWidth(816);
        window.setMaxHeight(1080);
        window.setMaxWidth(1920);
        window.setScene(scene);
        window.show();
    }
}
