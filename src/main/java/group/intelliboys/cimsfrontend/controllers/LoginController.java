package group.intelliboys.cimsfrontend.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Pane usernamePane;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public boolean isUsernameValid() {
        String username = usernameField.getText();

        if(username.length() < 8 || username.contains(" ")) {
            usernamePane.setStyle("-fx-border-width: 2; -fx-border-color: red;");
            usernameField.setTooltip(new Tooltip("Invalid Username!"));
            return false;
        }
        else {
            usernamePane.setStyle("-fx-border-width: 2; -fx-border-color: rgb(36, 76, 230);");
            return true;
        }
    }

    public void loginButtonClicked() {
        String username = usernameField.getText();
        String password = passwordField.getText();

    }
}