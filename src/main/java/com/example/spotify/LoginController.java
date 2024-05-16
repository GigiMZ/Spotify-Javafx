package com.example.spotify;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    @FXML
    private TextField username_l;

    @FXML
    private PasswordField password_l;

    @FXML
    private Button register_l;

    @FXML
    private void loginPressed() {
        if (username_l.getText().isEmpty() || password_l.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Fill every block and try again.");
            alert.showAndWait();
            return;
        } else if (username_l.getText().length() > 30) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Username is too long.");
            alert.showAndWait();
            return;
        } else if (username_l.getText().length() < 2) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Username is too short.");
            alert.showAndWait();
            return;
        } else if (password_l.getText().length() > 50) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("password is too long.");
            alert.showAndWait();
            return;
        } else if (password_l.getText().length() < 8) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("password is too short.");
            alert.showAndWait();
            return;
        }
        if (!DBInit.checkIfUserExists(this.username_l.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Can't Log In");
            alert.setContentText("Username or Password not found.");
            alert.showAndWait();
            return;
        } else if (!DBInit.PasswordMatch(this.username_l.getText(), DBInit.hashPassword(this.password_l.getText()))) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Can't Log In");
            alert.setContentText("Username or Password not found.");
            alert.showAndWait();
            return;
        }
    }
    @FXML
    private void registerPressed() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("register.fxml")));
        Stage window = (Stage) register_l.getScene().getWindow();
        window.setTitle("Sign Up");
        window.setScene(new Scene(root, 800, 700));
    }

}
