package group.intelliboys.cimsfrontend.controllers.admin_dashboard;

import group.intelliboys.cimsfrontend.App;
import group.intelliboys.cimsfrontend.models.AuthenticationTokenHolder;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {

    @FXML
    private TextField searchField;

    public static String searchFieldValue;

    @FXML
    private Pane contentPane;

    public static String crudOperationType;

    public static ObservableList<Object> listOfObject;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void showCustomersClicked() throws IOException {
        System.out.println("Show customers clicked!");

        crudOperationType = "Customers List";

        contentPane.getChildren().clear();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/crud-view.fxml"));
        Pane pane = fxmlLoader.load();

        contentPane.getChildren().add(pane);
    }

    public void showStaffsClicked() throws IOException {
        System.out.println("Show staffs clicked!");

        crudOperationType = "Staffs List";

        contentPane.getChildren().clear();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/crud-view.fxml"));
        Pane pane = fxmlLoader.load();

        contentPane.getChildren().add(pane);
    }

    public void inventoryClicked() throws IOException {
        System.out.println("Inventory clicked!");

        crudOperationType = "Inventory";

        contentPane.getChildren().clear();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/crud-view.fxml"));
        Pane pane = fxmlLoader.load();

        contentPane.getChildren().add(pane);
    }

    public void salesReportClicked() throws IOException {
        System.out.println("Sales report clicked!");
        contentPane.getChildren().clear();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/crud-view.fxml"));
        Pane pane = fxmlLoader.load();

        contentPane.getChildren().add(pane);
    }

    public void predictiveAnalysisClicked() throws IOException {
        System.out.println("Predictive analysis clicked!");
        contentPane.getChildren().clear();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/crud-view.fxml"));
        Pane pane = fxmlLoader.load();

        contentPane.getChildren().add(pane);
    }

    public void auditTrailClicked() throws IOException {
        System.out.println("Audit analysis clicked!");
        contentPane.getChildren().clear();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/crud-view.fxml"));
        Pane pane = fxmlLoader.load();

        contentPane.getChildren().add(pane);
    }

    public void searchCustomerTextChanged() throws IOException {
        crudOperationType = "Search";
        searchFieldValue = searchField.getText();

        contentPane.getChildren().clear();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/crud-view.fxml"));
        Pane pane = fxmlLoader.load();

        contentPane.getChildren().add(pane);
    }

    public void logoutButtonClicked() throws IOException {
        int decision = JOptionPane.showConfirmDialog(null, "DO YOU WANT TO LOGOUT");

        if (decision == 0) {
            AuthenticationTokenHolder.setToken(null);

            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/login-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            App.primaryStage.setScene(scene);
            App.primaryStage.centerOnScreen();
        }
    }
}
