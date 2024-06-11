package com.example.spotify;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

import io.github.cdimascio.dotenv.Dotenv;

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

}
