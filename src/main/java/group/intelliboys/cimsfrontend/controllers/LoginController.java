package group.intelliboys.cimsfrontend.controllers;

import group.intelliboys.cimsfrontend.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    // ======================== USERNAME =========================
    @FXML
    private Pane usernamePane;

    @FXML
    private TextField usernameField;

    @FXML
    private ImageView usernameStatus;
    // ===========================================================

    // ======================== PASSWORD =========================
    @FXML
    private Pane passwordPane;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ImageView passwordStatus;
    // ===========================================================

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public boolean isUsernameValid() {
        String username = usernameField.getText();

        if(username.length() < 8 || username.length() > 30 || username.contains(" ")) {
            usernamePane.setStyle("-fx-border-width: 2; -fx-border-color: red;");
            usernameStatus.setImage(new Image(Objects.requireNonNull(App.class.getResource("images/invalid.png")).toString()));
            usernameField.setTooltip(new Tooltip("Invalid Username!"));
            return false;
        }
        else {
            usernamePane.setStyle("-fx-border-width: 2; -fx-border-color: rgb(36, 76, 230);");
            usernameStatus.setImage(new Image(Objects.requireNonNull(App.class.getResource("images/check.png")).toString()));
            usernameField.setTooltip(null);
            return true;
        }
    }

    public boolean isPasswordValid() {
        String password = passwordField.getText();

        if(password.length() < 8 || password.length() > 30 || password.contains(" ")) {
            passwordPane.setStyle("-fx-border-width: 2; -fx-border-color: red;");
            passwordStatus.setImage(new Image(Objects.requireNonNull(App.class.getResource("images/invalid.png")).toString()));
            passwordField.setTooltip(new Tooltip("Invalid Password!"));
            return false;
        }
        else {
            passwordPane.setStyle("-fx-border-width: 2; -fx-border-color: rgb(36, 76, 230);");
            passwordStatus.setImage(new Image(Objects.requireNonNull(App.class.getResource("images/check.png")).toString()));
            passwordField.setTooltip(null);
            return true;
        }
    }

    public void loginButtonClicked() {
        if(isUsernameValid() && isPasswordValid()) {
            System.out.println("Hello World!");
        }
        else {
            System.out.println("Invalid!");
        }
    }
}