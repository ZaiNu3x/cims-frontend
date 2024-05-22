package group.intelliboys.cimsfrontend.controllers;

import com.google.gson.Gson;
import group.intelliboys.cimsfrontend.App;
import group.intelliboys.cimsfrontend.models.AuthenticationTokenHolder;
import group.intelliboys.cimsfrontend.models.LoginRequest;
import group.intelliboys.cimsfrontend.models.LoginResponse;
import group.intelliboys.cimsfrontend.services.JwtService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
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

    @FXML
    private Pane invalidCredentialErrorPane;

    @FXML
    private Pane serverErrorPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

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

    public void registerButtonClicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/registration-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        App.primaryStage.setScene(scene);
        App.primaryStage.centerOnScreen();
    }

    public void loginButtonClicked() {
        if (isUsernameValid() && isPasswordValid()) {
            String username = usernameField.getText();
            String password = passwordField.getText();

            LoginRequest loginRequest = new LoginRequest(username, password);
            HttpClient httpClient = HttpClient.newHttpClient();
            String requestBody = new Gson().toJson(loginRequest);

            try {
                HttpRequest request = HttpRequest.newBuilder(new URI("http://localhost:8080/login"))
                        .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                        .header("Content-Type", "application/json")
                        .timeout(Duration.ofSeconds(30))
                        .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                LoginResponse loginResponse = new Gson().fromJson(response.body(), LoginResponse.class);

                if (loginResponse.getToken() != null && loginResponse.getMessage().equalsIgnoreCase("Authentication Success!")) {
                    AuthenticationTokenHolder.setToken(loginResponse.getToken());

                    String role = JwtService.extractRole(AuthenticationTokenHolder.getToken());
                    FXMLLoader fxmlLoader;

                    switch (Objects.requireNonNull(role)) {
                        case "ROLE_ADMIN":
                            fxmlLoader = new FXMLLoader(App.class.getResource("views/admin-dashboard-view.fxml"));
                            Scene adminScene = new Scene(fxmlLoader.load());
                            App.primaryStage.setScene(adminScene);
                            App.primaryStage.centerOnScreen();
                            break;

                        case "ROLE_STAFF":
                            fxmlLoader = new FXMLLoader(App.class.getResource("views/staff-dashboard-view.fxml"));
                            Scene staffScene = new Scene(fxmlLoader.load());
                            App.primaryStage.setScene(staffScene);
                            App.primaryStage.centerOnScreen();
                            break;

                        default:
                            System.out.println("Error!");
                    }

                } else {
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            invalidCredentialErrorPane.setVisible(true);

                            try {
                                Thread.sleep(3000);
                                float opacity = 1f;

                                for (int i = 0; i < 10; i++) {
                                    opacity -= 0.1f;
                                    invalidCredentialErrorPane.setOpacity(opacity);
                                    Thread.sleep(80);
                                }
                                invalidCredentialErrorPane.setVisible(false);
                                invalidCredentialErrorPane.setOpacity(1);
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        }
                    });

                    thread.start();
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
            }
        } else {
            System.out.println("Invalid!");
        }
    }
}
