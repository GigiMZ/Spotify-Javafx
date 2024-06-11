package com.example.spotify;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    @FXML
    private TextField email_r;

    @FXML
    private TextField username_r;

    @FXML
    private PasswordField password_r;

    @FXML
    private DatePicker date;

    @FXML
    private RadioButton gender_man;

    @FXML
    private RadioButton gender_woman;

    @FXML
    private RadioButton gender_non;

    @FXML
    private Button login_r;

    @FXML
    private void registerPressed() throws IOException {
        if (email_r.getText().isEmpty() || username_r.getText().isEmpty() || password_r.getText().isEmpty() || date == null || !gender_man.isSelected() && !gender_woman.isSelected() && !gender_non.isSelected()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Fill every block and try again.");
            alert.showAndWait();
            return;
        }else if(username_r.getText().length() > 30) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Username is too long.");
            alert.showAndWait();
            return;
        } else if (username_r.getText().length() < 2) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Username is too short.");
            alert.showAndWait();
            return;
        }else if (password_r.getText().length() > 50) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("password is too long.");
            alert.showAndWait();
            return;
        } else if (password_r.getText().length() < 8) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("password is too short.");
            alert.showAndWait();
            return;
        }else if (email_r.getText().length() > 80) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Email is too long.");
            alert.showAndWait();
            return;
        }else if (!EmailDomainMatch(email_r.getText(), email_r.getText().indexOf("@"))) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Incorrect Email Domain.");
            alert.showAndWait();
            return;
        }

        String password_hash = DBInit.hashPassword(this.password_r.getText());
        LocalDate date = this.date.getValue();
        DBInit user = new DBInit(this.email_r.getText(), this.username_r.getText(), password_hash,
                date.getDayOfMonth() +"/"+ date.getMonthValue() +"/"+ date.getYear());
        user.addToDB();
        toMain();
    }
    private static boolean EmailDomainMatch(String email, int ampersand_index) throws FileNotFoundException {
        String email_domain = email.substring(ampersand_index+1);
        BufferedReader reader = new BufferedReader(new FileReader("src/main/java/Documents/EmailDomains.txt"));
        try {
            String domain = reader.readLine();
            while (domain != null) {
                if (email_domain.equals(domain)) {
                    return true;
                }
                domain = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    @FXML
    private void loginPressed() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
        Stage window = (Stage) login_r.getScene().getWindow();
        window.setTitle("Log In");
        window.setScene(new Scene(root, 800, 700));
    }

    @FXML
    private void toMain() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main.fxml")));
        Stage window = (Stage) login_r.getScene().getWindow();
        window.setTitle("Spotify");
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
