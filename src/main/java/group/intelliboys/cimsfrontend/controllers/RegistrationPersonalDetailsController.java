package group.intelliboys.cimsfrontend.controllers;

import group.intelliboys.cimsfrontend.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.ResourceBundle;

public class RegistrationPersonalDetailsController implements Initializable {

    // ======================== LASTNAME =========================
    @FXML
    private Pane lastnamePane;

    @FXML
    private TextField lastnameField;

    @FXML
    private ImageView lastnameStatus;
    // ===========================================================

    // ======================== FIRSTNAME =========================
    @FXML
    private Pane firstnamePane;

    @FXML
    private TextField firstnameField;

    @FXML
    private ImageView firstnameStatus;
    // ===========================================================

    // ======================== MIDDLENAME =========================
    @FXML
    private Pane middlenamePane;

    @FXML
    private TextField middlenameField;

    @FXML
    private ImageView middlenameStatus;
    // ===========================================================

    // ======================== GENDER =========================
    @FXML
    private Pane genderPane;

    @FXML
    private ComboBox<String> genderField;

    // ===========================================================

    // ======================== BIRTHDATE =========================
    @FXML
    private Pane birthDatePane;

    @FXML
    private DatePicker birthDateField;

    // ===========================================================

    // ======================== EMAIL =========================
    @FXML
    private Pane emailPane;

    @FXML
    private TextField emailField;

    @FXML
    private ImageView emailStatus;
    // ===========================================================

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genderField.getItems().addAll("Male", "Female");
    }

    public boolean isLastnameValid() {
        String lastname = lastnameField.getText();

        if (lastname.length() < 8 || lastname.length() > 30 || lastname.contains(" ")) {
            lastnamePane.setStyle("-fx-border-width: 2; -fx-border-color: red;");
            lastnameStatus.setImage(new Image(Objects.requireNonNull(App.class.getResource("images/invalid.png")).toString()));
            lastnameField.setTooltip(new Tooltip("Invalid Lastname!"));
            return false;
        } else {
            lastnamePane.setStyle("-fx-border-width: 2; -fx-border-color: rgb(36, 76, 230);");
            lastnameStatus.setImage(new Image(Objects.requireNonNull(App.class.getResource("images/check.png")).toString()));
            lastnameField.setTooltip(null);
            return true;
        }
    }

    public boolean isFirstnameValid() {
        String lastname = firstnameField.getText();

        if (lastname.length() < 8 || lastname.length() > 30 || lastname.contains(" ")) {
            firstnamePane.setStyle("-fx-border-width: 2; -fx-border-color: red;");
            firstnameStatus.setImage(new Image(Objects.requireNonNull(App.class.getResource("images/invalid.png")).toString()));
            firstnameField.setTooltip(new Tooltip("Invalid Firstname!"));
            return false;
        } else {
            firstnamePane.setStyle("-fx-border-width: 2; -fx-border-color: rgb(36, 76, 230);");
            firstnameStatus.setImage(new Image(Objects.requireNonNull(App.class.getResource("images/check.png")).toString()));
            firstnameField.setTooltip(null);
            return true;
        }
    }

    public boolean isMiddlenameValid() {
        String lastname = middlenameField.getText();

        if (lastname.length() < 8 || lastname.length() > 30 || lastname.contains(" ")) {
            middlenamePane.setStyle("-fx-border-width: 2; -fx-border-color: red;");
            middlenameStatus.setImage(new Image(Objects.requireNonNull(App.class.getResource("images/invalid.png")).toString()));
            middlenameField.setTooltip(new Tooltip("Invalid Middlename!"));
            return false;
        } else {
            middlenamePane.setStyle("-fx-border-width: 2; -fx-border-color: rgb(36, 76, 230);");
            middlenameStatus.setImage(new Image(Objects.requireNonNull(App.class.getResource("images/check.png")).toString()));
            middlenameField.setTooltip(null);
            return true;
        }
    }

    public boolean isGenderValid() {
        String gender = genderField.getValue();

        if (gender.isEmpty()) {
            genderPane.setStyle("-fx-border-width: 2; -fx-border-color: red;");
            genderField.setTooltip(new Tooltip("Invalid Gender!"));
            return false;
        } else {
            genderPane.setStyle("-fx-border-width: 2; -fx-border-color: rgb(36, 76, 230);");
            genderField.setTooltip(null);
            return true;
        }
    }

    public boolean isBirthDateValid() {
        int age = Period.between(birthDateField.getValue(), LocalDate.now()).getYears();

        if (age < 0 || age > 127) {
            birthDatePane.setStyle("-fx-border-width: 2; -fx-border-color: red;");
            birthDateField.setTooltip(new Tooltip("Invalid Birth Date!"));
            return false;
        } else {
            birthDatePane.setStyle("-fx-border-width: 2; -fx-border-color: rgb(36, 76, 230);");
            birthDateField.setTooltip(null);
            return true;
        }
    }

    public boolean isEmailValid() {
        String email = emailField.getText();

        if (email.endsWith("yahoo.com") || email.endsWith("gmail.com")) {
            emailPane.setStyle("-fx-border-width: 2; -fx-border-color: rgb(36, 76, 230);");
            emailStatus.setImage(new Image(Objects.requireNonNull(App.class.getResource("images/check.png")).toString()));
            emailField.setTooltip(null);
            return true;
        } else {
            emailPane.setStyle("-fx-border-width: 2; -fx-border-color: red;");
            emailStatus.setImage(new Image(Objects.requireNonNull(App.class.getResource("images/invalid.png")).toString()));
            emailField.setTooltip(new Tooltip("Invalid Emmail!"));
            return true;
        }
    }
}
