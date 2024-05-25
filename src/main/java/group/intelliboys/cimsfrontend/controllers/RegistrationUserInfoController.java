package group.intelliboys.cimsfrontend.controllers;

import com.google.gson.Gson;
import group.intelliboys.cimsfrontend.App;
import group.intelliboys.cimsfrontend.models.user.TmpUser;
import group.intelliboys.cimsfrontend.models.user.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ResourceBundle;

public class RegistrationUserInfoController implements Initializable {
    @FXML
    private Label lastNameLbl;

    @FXML
    private Label firstNameLbl;

    @FXML
    private Label middleNameLbl;

    @FXML
    private Label genderLbl;

    @FXML
    private Label birthDateLbl;

    @FXML
    private Label ageLbl;

    @FXML
    private Label addressLbl;

    @FXML
    private Label emailAddressLbl;

    @FXML
    private ImageView profilePic;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lastNameLbl.setText(RegistrationController.registeringUser.getLastName());
        firstNameLbl.setText(RegistrationController.registeringUser.getFirstName());
        middleNameLbl.setText(RegistrationController.registeringUser.getMiddleName());
        genderLbl.setText(RegistrationController.registeringUser.getSex());
        birthDateLbl.setText(RegistrationController.registeringUser.getBirthDate().toString());
        ageLbl.setText(Byte.toString(RegistrationController.registeringUser.getAge()));
        addressLbl.setText(RegistrationController.registeringUser.getAddress());
        emailAddressLbl.setText(RegistrationController.registeringUser.getEmail());

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(RegistrationController.registeringUser.getProfilePic());
        Image image = new Image(byteArrayInputStream);

        profilePic.setImage(image);
    }

    public void submitClicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/registration-success-dialog-view.fxml"));
        DialogPane successPane = fxmlLoader.load();

        HttpClient httpClient = HttpClient.newHttpClient();

        TmpUser tmpUser = TmpUser.builder()
                .username(RegistrationController.registeringUser.getUsername())
                .password(RegistrationController.registeringUser.getPassword())
                .lastName(RegistrationController.registeringUser.getLastName())
                .firstName(RegistrationController.registeringUser.getFirstName())
                .middleName(RegistrationController.registeringUser.getMiddleName())
                .sex(RegistrationController.registeringUser.getSex())
                .birthDate(RegistrationController.registeringUser.getBirthDate().toString())
                .age(RegistrationController.registeringUser.getAge())
                .address(RegistrationController.registeringUser.getAddress())
                .role(RegistrationController.registeringUser.getRole())
                .email(RegistrationController.registeringUser.getEmail())
                .profilePic(RegistrationController.registeringUser.getProfilePic())
                .build();

        String requestBody = new Gson().toJson(tmpUser);

        System.out.println(requestBody);

        try {
            HttpRequest request = HttpRequest.newBuilder(new URI("http://localhost:8080/api/v1/user/registration/user/save"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", "application/json")
                    .timeout(Duration.ofSeconds(30))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        }
        catch (Exception e) {

        }

        Dialog<String> successDialog = new Dialog<>();
        successDialog.setDialogPane(successPane);
        successDialog.setTitle("Registration Success!");
        successDialog.showAndWait();

        fxmlLoader = new FXMLLoader(App.class.getResource("views/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        App.primaryStage.setScene(scene);
        App.primaryStage.centerOnScreen();
    }
}
