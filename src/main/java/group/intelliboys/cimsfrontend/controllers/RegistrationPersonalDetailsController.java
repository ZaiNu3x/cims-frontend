package group.intelliboys.cimsfrontend.controllers;

import group.intelliboys.cimsfrontend.App;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

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

    // ======================== HOUSE NUMBER =========================
    @FXML
    private Pane houseNumPane;

    @FXML
    private TextField houseNumField;

    @FXML
    private ImageView houseNumStatus;
    // ===========================================================

    // ======================== CITY =========================
    @FXML
    private Pane cityPane;

    @FXML
    private ComboBox<String> cityField;

    // ===========================================================

    // ======================== BARANGAY =========================
    @FXML
    private Pane barangayPane;

    @FXML
    private ComboBox<String> barangayField;

    // ===========================================================

    @FXML
    private Circle profilePicPane;

    @FXML
    private Button selectProfilePic;

    public static String formId;
    private ByteArrayOutputStream byteArrayOutputStream;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        formId = generateFormId();

        genderField.getItems().addAll("Male", "Female");
        cityField.getItems().addAll("NCR - Taguig City", "NCR - Makati City");
        profilePicPane.setFill(new ImagePattern(new Image(Objects.requireNonNull(App.class.getResource("images/profile-picture.png")).toString())));
    }

    public boolean isLastnameValid() {
        String lastname = lastnameField.getText();

        /*
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9\\\\s]");
        Matcher matcher = pattern.matcher(lastname);
         */

        if (lastname.length() < 3 || lastname.length() > 30) {
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
        String firstname = firstnameField.getText();

        /*
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9\\\\s]");
        Matcher matcher = pattern.matcher(firstname);
         */

        if (firstname.length() < 3 || firstname.length() > 30) {
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
        String firstname = middlenameField.getText();

        /*
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9\\\\s]");
        Matcher matcher = pattern.matcher(firstname);
         */

        if (firstname.length() < 3 || firstname.length() > 30) {
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

        if (gender.length() < 4) {
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
            emailField.setTooltip(new Tooltip("Invalid Email!"));
            return true;
        }
    }

    private boolean isEmailExists() {
        String email = emailField.getText();
        HttpClient httpClient = HttpClient.newHttpClient();

        try {
            HttpRequest request = HttpRequest.newBuilder(new URI("http://localhost:8080/api/v1/user/registration/find/email/exists/" + email + "/" + formId))
                    .GET()
                    .header("Content-Type", "application/json")
                    .timeout(Duration.ofSeconds(30))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            boolean isExists = Boolean.parseBoolean(response.body());

            if (!isExists) {
                emailPane.setStyle("-fx-border-width: 2; -fx-border-color: rgb(36, 76, 230);");
                emailStatus.setImage(new Image(Objects.requireNonNull(App.class.getResource("images/check.png")).toString()));
                emailField.setTooltip(null);
                return false;
            } else {
                emailPane.setStyle("-fx-border-width: 2; -fx-border-color: red;");
                emailStatus.setImage(new Image(Objects.requireNonNull(App.class.getResource("images/invalid.png")).toString()));
                emailField.setTooltip(new Tooltip("Email already exists!"));
                return true;
            }

        } catch (Exception e) {
            return false;
        }
    }

    public boolean isHouseNumValid() {
        String houseNum = houseNumField.getText();

        if (houseNum.length() < 3 || houseNum.length() > 127) {
            houseNumPane.setStyle("-fx-border-width: 2; -fx-border-color: red;");
            houseNumStatus.setImage(new Image(Objects.requireNonNull(App.class.getResource("images/invalid.png")).toString()));
            houseNumField.setTooltip(new Tooltip("Invalid House Number!"));
            return false;
        } else {
            houseNumPane.setStyle("-fx-border-width: 2; -fx-border-color: rgb(36, 76, 230);");
            houseNumStatus.setImage(new Image(Objects.requireNonNull(App.class.getResource("images/check.png")).toString()));
            houseNumField.setTooltip(null);
            return true;
        }
    }

    public boolean isCityValid() {
        String city = cityField.getValue();

        if (city.isEmpty()) {
            cityPane.setStyle("-fx-border-width: 2; -fx-border-color: red;");
            cityField.setTooltip(new Tooltip("Invalid City!"));
            return false;
        } else {
            cityPane.setStyle("-fx-border-width: 2; -fx-border-color: rgb(36, 76, 230);");
            cityField.setTooltip(null);
            return true;
        }
    }

    public boolean isBarangayValid() {
        String barangay = barangayField.getValue();

        if (barangay == null) {
            barangayPane.setStyle("-fx-border-width: 2; -fx-border-color: red;");
            barangayField.setTooltip(new Tooltip("Invalid Barangay!"));
            return false;
        } else {
            barangayPane.setStyle("-fx-border-width: 2; -fx-border-color: rgb(36, 76, 230);");
            barangayField.setTooltip(null);
            return true;
        }
    }

    public void cityChangedValue() {

        if (isCityValid()) {
            String city = cityField.getValue();

            if (city.equals("NCR - Taguig City")) {

                barangayField.getItems().clear();

                List<String> barangays = List.of("Bagumbayan",
                        "Bambang",
                        "Calzada",
                        "Central Bicutan",
                        "Central Signal Village",
                        "Fort Bonifacio",
                        "Hagonoy",
                        "Ibayo-Tipas",
                        "Katuparan",
                        "Ligid-Tipas",
                        "Lower Bicutan",
                        "Maharlika Village",
                        "Napindan",
                        "New Lower Bicutan",
                        "North Daang Hari",
                        "North Signal Village",
                        "Palingon",
                        "Pinagsama",
                        "San Miguel",
                        "Santa Ana",
                        "Tuktukan",
                        "Upper Bicutan",
                        "Ususan",
                        "Wawa",
                        "Western Bicutan");

                barangayField.getItems().addAll(barangays);

            } else if (city.equals("NCR - Makati City")) {

                barangayField.getItems().clear();

                List<String> barangays = List.of("Bangkal",
                        "Bel-Air",
                        "Carmona",
                        "Cembo",
                        "Comembo",
                        "Dasmarinas",
                        "East Rembo",
                        "Forbes Park",
                        "Guadalupe Nuevo",
                        "Guadalupe Viejo",
                        "Kasilawan",
                        "La Paz",
                        "Magallanes",
                        "Olympia",
                        "Palanan",
                        "Pembo",
                        "Pinagkaisahan",
                        "Pio del Pilar",
                        "Pitogo",
                        "Poblacion",
                        "Post Proper Northside",
                        "Post Proper Southside",
                        "Rizal",
                        "San Antonio",
                        "San Isidro",
                        "San Lorenzo",
                        "Santa Cruz",
                        "Singkamas",
                        "South Cembo",
                        "Tejeros",
                        "Urdaneta",
                        "Valenzuela");

                barangayField.getItems().addAll(barangays);
            }
        }
    }

    public void barangayChangedValue() {
        isBarangayValid();
    }

    public void selectProfilePicButtonClicked() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png"));

        File selectedImageFile = fileChooser.showOpenDialog(App.primaryStage);

        if (selectedImageFile != null) {
            BufferedImage bufferedImage = ImageIO.read(selectedImageFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);

            RegistrationController.registeringUser.setProfilePic(byteArrayOutputStream.toByteArray());

            profilePicPane.setFill(new ImagePattern(image));
            profilePicPane.setStyle("-fx-stroke-width: 2; -fx-stroke: rgb(36, 76, 230);");
        } else {
            profilePicPane.setFill(null);
            profilePicPane.setStyle("-fx-stroke-width: 2; -fx-stroke: red;");
        }
    }

    private boolean isFormValid() {
        return isLastnameValid() && isFirstnameValid() && isMiddlenameValid() && isGenderValid()
                && isBirthDateValid() && isEmailValid() && isHouseNumValid() && isCityValid()
                && isBarangayValid() && !isEmailExists();
    }

    public void nextButtonClicked() throws IOException {
        if (isFormValid()) {
            RegistrationController.registeringUser.setLastName(lastnameField.getText());
            RegistrationController.registeringUser.setFirstName(firstnameField.getText());
            RegistrationController.registeringUser.setLastName(lastnameField.getText());
            RegistrationController.registeringUser.setMiddleName(middlenameField.getText());
            RegistrationController.registeringUser.setSex(genderField.getValue());
            RegistrationController.registeringUser.setBirthDate(birthDateField.getValue());
            RegistrationController.registeringUser.setEmail(emailField.getText());
            RegistrationController.registeringUser.setAge((byte) Period.between(birthDateField.getValue(), LocalDate.now()).getYears());
            RegistrationController.registeringUser.setRole("ROLE_ADMIN");
            RegistrationController.registeringUser.setProfilePic(byteArrayOutputStream.toByteArray());

            StringBuilder address = new StringBuilder();

            address.append(houseNumField.getText() + ", ");
            address.append(barangayField.getValue() + ", ");
            address.append(cityField.getValue());

            RegistrationController.registeringUser.setAddress(new String(address));

            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/otp-dialog-view.fxml"));
            DialogPane otpPane = fxmlLoader.load();

            Dialog<String> otpDialog = new Dialog<>();
            otpDialog.setDialogPane(otpPane);
            otpDialog.setTitle("OTP Authentication");
            otpDialog.showAndWait();

        } else {
            System.out.println("Invalid!");
        }
    }

    private String generateFormId() {
        Random random = new Random();
        StringBuilder formId = new StringBuilder();

        Collection<Integer> randomNumbers = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            int randomNum = random.nextInt(0, 9);

            formId.append(randomNum);
        }

        return new String(formId);
    }
}
