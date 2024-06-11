package com.example.spotify;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.ResourceBundle;

public class EditController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    @FXML
    private Button edit_photo;

    @FXML
    private Button edit_email;

    @FXML
    private Button edit_password;

    @FXML
    private Button cancel;

    @FXML
    private Button save;

    @FXML
    private ImageView photo;

    @FXML
    public void cancel() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("profile.fxml")));
        Stage window = (Stage) cancel.getScene().getWindow();
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
    public void photo_choose() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Photo File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
        File file = fileChooser.showOpenDialog(photo.getScene().getWindow());
        File sourceFile = new File(file.getAbsolutePath());
        File destinationDirectory = new File("C:\\Users\\giorg\\IdeaProjects\\Spotify\\src\\main\\resources\\com\\example\\spotify\\pictures");
        Path sourcePath = sourceFile.toPath();
        Path destinationPath = Path.of("C:\\Users\\giorg\\IdeaProjects\\Spotify\\src\\main\\resources\\com\\example\\spotify\\pictures\\avatar.jpg");
        try {
            Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            destinationPath = Path.of("C:\\Users\\giorg\\IdeaProjects\\Spotify\\src\\main\\resources\\com\\example\\spotify\\pictures\\avatar.png");
            Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to move the file.");
        }
        System.out.println("Invalid file.");
    }
}
