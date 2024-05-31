package group.intelliboys.cimsfrontend.controllers.login_registration;

import group.intelliboys.cimsfrontend.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import javax.swing.*;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Objects;
import java.util.ResourceBundle;

public class ForgotPasswordController implements Initializable {

    @FXML
    private Pane emailPane;

    @FXML
    private TextField emailField;

    @FXML
    private ImageView emailStatus;
    // ===========================================================

    @FXML
    private Pane otpPane;

    @FXML
    private PasswordField otpField;

    @FXML
    private ImageView otpStatus;
    // ===========================================================

    // ======================== PASSWORD =========================
    @FXML
    private Pane passwordPane;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ImageView passwordStatus;
    // ===========================================================

    // ======================== CONFIRM PASSWORD =========================
    @FXML
    private Pane confirmPasswordPane;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private ImageView confirmPasswordStatus;
    // ===========================================================

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initializing.........");
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
            emailField.setTooltip(new Tooltip("Invalid Email!"));
            return true;
        }
    }

    public boolean isPasswordValid() {
        String password = passwordField.getText();

        if (password.length() < 8 || password.length() > 30 || password.contains(" ")) {
            passwordPane.setStyle("-fx-border-width: 2; -fx-border-color: red;");
            passwordStatus.setImage(new Image(Objects.requireNonNull(App.class.getResource("images/invalid.png")).toString()));
            passwordField.setTooltip(new Tooltip("Invalid Password!"));

            return false;
        } else {
            passwordPane.setStyle("-fx-border-width: 2; -fx-border-color: rgb(36, 76, 230);");
            passwordStatus.setImage(new Image(Objects.requireNonNull(App.class.getResource("images/check.png")).toString()));
            passwordField.setTooltip(null);

            return true;
        }
    }

    public boolean isConfirmPasswordSame() {
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (password.isEmpty() || !password.equals(confirmPassword)) {
            confirmPasswordPane.setStyle("-fx-border-width: 2; -fx-border-color: red;");
            confirmPasswordStatus.setImage(new Image(Objects.requireNonNull(App.class.getResource("images/invalid.png")).toString()));
            confirmPasswordField.setTooltip(new Tooltip("Confirm password is not same with password!"));

            return false;
        } else {
            confirmPasswordPane.setStyle("-fx-border-width: 2; -fx-border-color: rgb(36, 76, 230);");
            confirmPasswordStatus.setImage(new Image(Objects.requireNonNull(App.class.getResource("images/check.png")).toString()));
            confirmPasswordField.setTooltip(null);
            return true;
        }
    }

    private boolean isFormValid() {
        return isEmailValid() && isPasswordValid() && isConfirmPasswordSame();
    }

    public void changePassword() {
        if (isFormValid() && otpField.getText().isEmpty()) {
            HttpClient httpClient = HttpClient.newHttpClient();

            String email = emailField.getText();

            System.out.println(email);

            try {
                HttpRequest request = HttpRequest.newBuilder(new URI("http://localhost:8080/api/v1/forgot_password/create/" + email))
                        .GET()
                        .header("Content-Type", "application/json")
                        .timeout(Duration.ofSeconds(30))
                        .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                JOptionPane.showMessageDialog(null, "OTP SENT!");

            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (isFormValid() && otpField.getText().length() >= 6) {
            HttpClient httpClient = HttpClient.newHttpClient();

            String email = emailField.getText();
            String otp = otpField.getText();

            System.out.println(email);

            try {
                HttpRequest request = HttpRequest.newBuilder(new URI("http://localhost:8080/api/v1/forgot_password/validate/" + email + "/" + otp + "/" + confirmPasswordField.getText()))
                        .GET()
                        .header("Content-Type", "application/json")
                        .timeout(Duration.ofSeconds(30))
                        .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.body().contains("true")) {
                    JOptionPane.showMessageDialog(null, "Password changed!");

                    LoginController.forgotPasswordDialog.close();
                }
                else {
                    System.out.println("Error!");
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
