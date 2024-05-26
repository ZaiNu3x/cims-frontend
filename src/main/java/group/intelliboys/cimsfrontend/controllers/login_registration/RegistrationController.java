package group.intelliboys.cimsfrontend.controllers.login_registration;

import group.intelliboys.cimsfrontend.App;
import group.intelliboys.cimsfrontend.models.user.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Objects;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {

    @FXML
    public static Slider registrationPhase;

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

    // ======================== CONFIRM PASSWORD =========================
    @FXML
    private Pane confirmPasswordPane;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private ImageView confirmPasswordStatus;
    // ===========================================================

    @FXML
    private Pane serverErrorPane;

    @FXML
    private Pane usernameExistsPane;

    @FXML
    private Pane authenticationPane;

    @FXML
    private ScrollPane personalDetailsPane;

    public static User registeringUser = new User();

    public static ScrollPane personalDetailsPaneRef;

    public static Slider sliderRef;

    // ======================================== AUTHENTICATION FORM ========================================

    public boolean isUsernameValid() {
        String username = usernameField.getText();

        if (username.length() < 8 || username.length() > 30 || username.contains(" ")) {
            usernamePane.setStyle("-fx-border-width: 2; -fx-border-color: red;");
            usernameStatus.setImage(new Image(Objects.requireNonNull(App.class.getResource("images/invalid.png")).toString()));
            usernameField.setTooltip(new Tooltip("Invalid Username!"));
            return false;
        } else {
            usernamePane.setStyle("-fx-border-width: 2; -fx-border-color: rgb(36, 76, 230);");
            usernameStatus.setImage(new Image(Objects.requireNonNull(App.class.getResource("images/check.png")).toString()));
            usernameField.setTooltip(null);
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

    private boolean isUsernameExists() {
        String username = usernameField.getText();

        HttpClient httpClient = HttpClient.newHttpClient();

        try {
            HttpRequest request = HttpRequest.newBuilder(new URI("http://localhost:8080/api/v1/user/registration/find/exists/" + username))
                    .GET()
                    .header("Content-Type", "application/json")
                    .timeout(Duration.ofSeconds(30))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            boolean isExists = Boolean.parseBoolean(response.body());

            if (isExists) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        usernameExistsPane.setVisible(true);

                        try {
                            Thread.sleep(3000);
                            float opacity = 1f;

                            for (int i = 0; i < 10; i++) {
                                opacity -= 0.1f;
                                usernameExistsPane.setOpacity(opacity);
                                Thread.sleep(80);
                            }
                            usernameExistsPane.setVisible(false);
                            usernameExistsPane.setOpacity(1);
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                });
                thread.start();

                usernamePane.setStyle("-fx-border-width: 2; -fx-border-color: red;");
                usernameStatus.setImage(new Image(Objects.requireNonNull(App.class.getResource("images/invalid.png")).toString()));
                usernameField.setTooltip(new Tooltip("Username already exists!"));

                return true;
            } else {
                System.out.println("Username not exists!");
                return false;
            }

        } catch (Exception e) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    serverErrorPane.setVisible(true);

                    try {
                        Thread.sleep(3000);
                        float opacity = 1f;

                        for (int i = 0; i < 10; i++) {
                            opacity -= 0.1f;
                            serverErrorPane.setOpacity(opacity);
                            Thread.sleep(80);
                        }
                        serverErrorPane.setVisible(false);
                        serverErrorPane.setOpacity(1);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            });

            thread.start();
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

    // ======================================================================================================

    // ========================================= PERSONAL DETAILS ===========================================

    // ======================================================================================================

    public void nextButtonClicked() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (isUsernameValid() && isPasswordValid() && isConfirmPasswordSame() && !isUsernameExists()) {
            registeringUser.setUsername(username);
            registeringUser.setPassword(password);

            FXMLLoader loader = new FXMLLoader(App.class.getResource("views/registration-personal-details-pane-view.fxml"));
            Pane pane = loader.load();

            authenticationPane.setVisible(false);
            personalDetailsPane.setContent(pane);
            personalDetailsPane.setVisible(true);
        }
    }

    public void backToLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        App.primaryStage.setScene(scene);
        App.primaryStage.centerOnScreen();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        personalDetailsPaneRef = personalDetailsPane;
        sliderRef = registrationPhase;
    }
}
