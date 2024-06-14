package com.example.spotify;

import com.google.api.services.youtube.YouTube;
import io.github.cdimascio.dotenv.Dotenv;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ResourceBundle;

public class SearchController extends MainController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    @FXML
    private TextField track_search;

    @FXML
    private HBox content;

    @FXML
    public void search() throws GeneralSecurityException, IOException, InterruptedException {
        Dotenv dotenv = Dotenv.load();
        String search = this.track_search.getText();
        YouTube youtubeService = InitMP3.getService();
        String videoId = InitMP3.searchTopResult(youtubeService, search);
        if (videoId != null) {
            String videoUrl = "https://www.youtube.com/watch?v="+videoId;
            String path = "file:///"+dotenv.get("DIRECTORY")+"Spotify/src/main/resources/com/example/spotify/Tracks";
            InitMP3.downloadVideo(videoUrl, path);
//            InitMP3.convertToMP3(path);
        } else {
            System.out.println("Video Id was not found.");
        }
    }
}
