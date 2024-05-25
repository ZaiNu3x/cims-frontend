package group.intelliboys.cimsfrontend.controllers;

import group.intelliboys.cimsfrontend.App;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import javax.swing.*;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class OtpDialogController implements Initializable {

    @FXML
    private Label timerLabel;
    @FXML
    private TextField otpField;
    @FXML
    private Pane otpPane;
    @FXML
    private ImageView otpStatus;
    @FXML
    private Label resendOtpLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AtomicInteger seconds = new AtomicInteger(10);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            if (seconds.get() == 0) {
                timerLabel.setText("0");
                resendOtpLabel.setVisible(true);
            } else {
                timerLabel.setText(Integer.toString(seconds.get()));
                seconds.decrementAndGet();
            }
        }));
        timeline.setCycleCount(seconds.get() + 1);
        timeline.play();
    }

    public void validateOtp() {
        String otp = otpField.getText();

        if (otp.length() < 6) {
            otpPane.setStyle("-fx-border-width: 2; -fx-border-color: red;");
            otpStatus.setImage(new Image(Objects.requireNonNull(App.class.getResource("images/invalid.png")).toString()));
            otpField.setTooltip(new Tooltip("Invalid Lastname!"));
        } else {
            otpPane.setStyle("-fx-border-width: 2; -fx-border-color: rgb(36, 76, 230);");
            otpStatus.setImage(new Image(Objects.requireNonNull(App.class.getResource("images/check.png")).toString()));
            otpField.setTooltip(null);

            String formId = RegistrationPersonalDetailsController.formId;
            String otpValue = otpField.getText();
            HttpClient httpClient = HttpClient.newHttpClient();

            try {
                HttpRequest request = HttpRequest.newBuilder(new URI("http://localhost:8080/api/v1/user/registration/otp/validate/" + formId + "/" + otp))
                        .GET()
                        .header("Content-Type", "application/json")
                        .timeout(java.time.Duration.ofSeconds(30))
                        .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                boolean isExists = Boolean.parseBoolean(response.body());

                if (isExists) {
                    FXMLLoader loader = new FXMLLoader(App.class.getResource("views/registration-userinfo-review-view.fxml"));
                    Pane pane = loader.load();

                    RegistrationController.personalDetailsPaneRef.setContent(pane);
                    RegistrationController.personalDetailsPaneRef.setVisible(true);

                    JOptionPane.showMessageDialog(null, "VALID OTP!");
                } else {
                    JOptionPane.showMessageDialog(null, "INVALID OTP!");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
