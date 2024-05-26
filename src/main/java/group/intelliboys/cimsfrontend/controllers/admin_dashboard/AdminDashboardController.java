package group.intelliboys.cimsfrontend.controllers.admin_dashboard;

import group.intelliboys.cimsfrontend.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {

    @FXML
    private TextField searchField;
    @FXML
    private Pane contentPane;

    public static String crudOperationType;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

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
}
