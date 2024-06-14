package com.example.spotify;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("search.fxml")));
        stage.setTitle("Register");
        Scene scene = new Scene(root, 800, 560);
        stage.setMinHeight(608);
        stage.setMinWidth(816);
        stage.setMaxHeight(1080);
        stage.setMaxWidth(1920);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
