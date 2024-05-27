package group.intelliboys.cimsfrontend.controllers.admin_dashboard;

import group.intelliboys.cimsfrontend.App;
import group.intelliboys.cimsfrontend.CrudOperation;
import group.intelliboys.cimsfrontend.models.AuthenticationTokenHolder;
import group.intelliboys.cimsfrontend.models.customer.Customer;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.SneakyThrows;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class CrudController implements Initializable {

    @FXML
    private Label crudLbl;

    @FXML
    private TableView<Object> objectsTable;

    public static Dialog<ButtonType> dialog;
    public static Customer selectedCustomer;

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String crudType = AdminDashboardController.crudOperationType;

        if (crudType.equals("Customers List")) {
            crudLbl.setText(crudType);

            AdminDashboardController.listOfObject = FXCollections.observableArrayList(Objects.requireNonNull(CrudOperation.fetchObjects("http://localhost:8080/api/v1/customer/find/all",
                    AuthenticationTokenHolder.getToken())));

            List<String> listOfFieldName = List.of("LastName", "FirstName", "MiddleName", "Email");

            listOfFieldName.forEach(fieldName -> {
                TableColumn<Object, String> fieldColumn = new TableColumn<Object, String>(fieldName);
                fieldColumn.setStyle("-fx-font-size: 18");
                fieldColumn.setCellValueFactory(new PropertyValueFactory<>(fieldName));

                objectsTable.getColumns().add(fieldColumn);
            });

            objectsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
            objectsTable.setItems(AdminDashboardController.listOfObject);

        } else if (crudType.equals("Staffs List")) {
            crudLbl.setText(crudType);
            crudLbl.setText(crudType);

            List<String> listOfFieldName = List.of("Id", "FirstName", "LastName", "Email");

            listOfFieldName.forEach(fieldName -> {
                TableColumn<Object, String> fieldColumn = new TableColumn<Object, String>(fieldName);
                fieldColumn.setStyle("-fx-font-size: 18");
                fieldColumn.setCellValueFactory(new PropertyValueFactory<>(fieldName));

                objectsTable.getColumns().add(fieldColumn);
            });

            objectsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
            //objectsTable.setItems(listOfObject);
        } else if (crudType.equals("Inventory")) {
            crudLbl.setText(crudType);
            crudLbl.setText(crudType);

            List<String> listOfFieldName = List.of("Id", "Name", "Brand", "Price", "Quantity");

            listOfFieldName.forEach(fieldName -> {
                TableColumn<Object, String> fieldColumn = new TableColumn<Object, String>(fieldName);
                fieldColumn.setStyle("-fx-font-size: 18");
                fieldColumn.setCellValueFactory(new PropertyValueFactory<>(fieldName));

                objectsTable.getColumns().add(fieldColumn);
            });

            objectsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
            //objectsTable.setItems(listOfObject);
        }
    }

    public static String crudOperation;

    public void addClicked() throws IOException {
        dialog = new Dialog<>();
        crudOperation = "add";

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/customer-registration-form.fxml"));
        DialogPane dialogPane = fxmlLoader.load();

        String operation = AdminDashboardController.crudOperationType;

        if (operation.equals("Customers List")) {
            dialog.setTitle("ADD CUSTOMER");

        } else if (operation.equals("Staffs List")) {
            dialog.setTitle("ADD STAFF");
        } else if (operation.equals("Inventory")) {
            dialog.setTitle("ADD STOCK");
        }

        dialog.setDialogPane(dialogPane);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        dialog.showAndWait();

        AdminDashboardController.listOfObject = FXCollections.observableArrayList(Objects.requireNonNull(CrudOperation.fetchObjects("http://localhost:8080/api/v1/customer/find/all",
                AuthenticationTokenHolder.getToken())));

        objectsTable.setItems(AdminDashboardController.listOfObject);
    }

    public void updateClicked() throws IOException {
        dialog = new Dialog<>();
        crudOperation = "update";
        selectedCustomer = (Customer) objectsTable.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/customer-registration-form.fxml"));
        DialogPane dialogPane = fxmlLoader.load();

        String operation = AdminDashboardController.crudOperationType;

        if (operation.equals("Customers List")) {
            dialog.setTitle("UPDATE CUSTOMER");

        } else if (operation.equals("Staffs List")) {
            dialog.setTitle("UPDATE STAFF");
        } else if (operation.equals("Inventory")) {
            dialog.setTitle("UPDATE STOCK");
        }

        dialog.setDialogPane(dialogPane);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        dialog.showAndWait();

        AdminDashboardController.listOfObject = FXCollections.observableArrayList(Objects.requireNonNull(CrudOperation.fetchObjects("http://localhost:8080/api/v1/customer/find/all",
                AuthenticationTokenHolder.getToken())));

        objectsTable.setItems(AdminDashboardController.listOfObject);
        AdminDashboardController.listOfObject.remove(selectedCustomer);
    }

    public void deleteClicked() {
        try {
            Customer selectionModel = (Customer) objectsTable.getSelectionModel().getSelectedItem();
            int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure to delete " + selectionModel.getFirstName() + " " + selectionModel.getLastName());

            if (confirmation == 0) {
                CrudOperation.deleteObject("http://localhost:8080/api/v1/customer/delete/", AuthenticationTokenHolder.getToken(), selectionModel.getId());

                AdminDashboardController.listOfObject = FXCollections.observableArrayList(Objects.requireNonNull(CrudOperation.fetchObjects("http://localhost:8080/api/v1/customer/find/all",
                        AuthenticationTokenHolder.getToken())));

                objectsTable.setItems(AdminDashboardController.listOfObject);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "PLEASE SELECT RECORD!");
        }
    }
}
