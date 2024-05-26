package group.intelliboys.cimsfrontend.controllers.admin_dashboard;

import group.intelliboys.cimsfrontend.App;
import group.intelliboys.cimsfrontend.models.customer.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class CrudController implements Initializable {

    @FXML
    private Label crudLbl;

    @FXML
    private TableView<Object> objectsTable;

    private ObservableList<Object> listOfObject;

    public static Dialog<String> dialog;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String crudType = AdminDashboardController.crudOperationType;

        if (crudType.equals("Customers List")) {
            crudLbl.setText(crudType);

            listOfObject = FXCollections.observableArrayList(
                    new Customer("Maraon", "Jerome", "Dela Peña", "Male", LocalDate.parse("2001-04-08"), (byte) 23, "zainu3x007@gmail.com", "PNR Site", null),
                    new Customer("Maraon", "Jerome", "Dela Peña", "Male", LocalDate.parse("2001-04-08"), (byte) 23, "zainu3x007@gmail.com", "PNR Site", null),
                    new Customer("Maraon", "Jerome", "Dela Peña", "Male", LocalDate.parse("2001-04-08"), (byte) 23, "zainu3x007@gmail.com", "PNR Site", null),
                    new Customer("Maraon", "Jerome", "Dela Peña", "Male", LocalDate.parse("2001-04-08"), (byte) 23, "zainu3x007@gmail.com", "PNR Site", null),
                    new Customer("Maraon", "Jerome", "Dela Peña", "Male", LocalDate.parse("2001-04-08"), (byte) 23, "zainu3x007@gmail.com", "PNR Site", null)
            );

            List<String> listOfFieldName = List.of("LastName", "FirstName", "MiddleName", "Email");

            listOfFieldName.forEach(fieldName -> {
                TableColumn<Object, String> fieldColumn = new TableColumn<Object, String>(fieldName);
                fieldColumn.setStyle("-fx-font-size: 18");
                fieldColumn.setCellValueFactory(new PropertyValueFactory<>(fieldName));

                objectsTable.getColumns().add(fieldColumn);
            });

            objectsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
            objectsTable.setItems(listOfObject);

        } else if (crudType.equals("Staffs List")) {
            crudLbl.setText(crudType);
            crudLbl.setText(crudType);

            listOfObject = FXCollections.observableArrayList(
                    new Customer("Maraon", "Jerome", "Dela Peña", "Male", LocalDate.parse("2001-04-08"), (byte) 23, "zainu3x007@gmail.com", "PNR Site", null),
                    new Customer("Maraon", "Jerome", "Dela Peña", "Male", LocalDate.parse("2001-04-08"), (byte) 23, "zainu3x007@gmail.com", "PNR Site", null),
                    new Customer("Maraon", "Jerome", "Dela Peña", "Male", LocalDate.parse("2001-04-08"), (byte) 23, "zainu3x007@gmail.com", "PNR Site", null),
                    new Customer("Maraon", "Jerome", "Dela Peña", "Male", LocalDate.parse("2001-04-08"), (byte) 23, "zainu3x007@gmail.com", "PNR Site", null),
                    new Customer("Maraon", "Jerome", "Dela Peña", "Male", LocalDate.parse("2001-04-08"), (byte) 23, "zainu3x007@gmail.com", "PNR Site", null)
            );

            List<String> listOfFieldName = List.of("LastName", "FirstName", "MiddleName", "Email");

            listOfFieldName.forEach(fieldName -> {
                TableColumn<Object, String> fieldColumn = new TableColumn<Object, String>(fieldName);
                fieldColumn.setStyle("-fx-font-size: 18");
                fieldColumn.setCellValueFactory(new PropertyValueFactory<>(fieldName));

                objectsTable.getColumns().add(fieldColumn);
            });

            objectsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
            objectsTable.setItems(listOfObject);
        } else if (crudType.equals("Inventory")) {
            crudLbl.setText(crudType);
            crudLbl.setText(crudType);

            listOfObject = FXCollections.observableArrayList(
                    new Customer("Maraon", "Jerome", "Dela Peña", "Male", LocalDate.parse("2001-04-08"), (byte) 23, "zainu3x007@gmail.com", "PNR Site", null),
                    new Customer("Maraon", "Jerome", "Dela Peña", "Male", LocalDate.parse("2001-04-08"), (byte) 23, "zainu3x007@gmail.com", "PNR Site", null),
                    new Customer("Maraon", "Jerome", "Dela Peña", "Male", LocalDate.parse("2001-04-08"), (byte) 23, "zainu3x007@gmail.com", "PNR Site", null),
                    new Customer("Maraon", "Jerome", "Dela Peña", "Male", LocalDate.parse("2001-04-08"), (byte) 23, "zainu3x007@gmail.com", "PNR Site", null),
                    new Customer("Maraon", "Jerome", "Dela Peña", "Male", LocalDate.parse("2001-04-08"), (byte) 23, "zainu3x007@gmail.com", "PNR Site", null)
            );

            List<String> listOfFieldName = List.of("LastName", "FirstName", "MiddleName", "Email");

            listOfFieldName.forEach(fieldName -> {
                TableColumn<Object, String> fieldColumn = new TableColumn<Object, String>(fieldName);
                fieldColumn.setStyle("-fx-font-size: 18");
                fieldColumn.setCellValueFactory(new PropertyValueFactory<>(fieldName));

                objectsTable.getColumns().add(fieldColumn);
            });

            objectsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
            objectsTable.setItems(listOfObject);
        }
    }

    public void addClicked() throws IOException {
        dialog = new Dialog<>();
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
        dialog.showAndWait();
    }

    public void updateClicked() throws IOException {
        dialog = new Dialog<>();
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
        dialog.showAndWait();
    }

    public void deleteClicked() {
        try {
            Customer selectionModel = (Customer) objectsTable.getSelectionModel().getSelectedItem();
            int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure to delete " + selectionModel.getFirstName() + " " + selectionModel.getLastName());

            if (confirmation == 0) {
                listOfObject.remove(selectionModel);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "PLEASE SELECT RECORD!");
        }
    }
}
