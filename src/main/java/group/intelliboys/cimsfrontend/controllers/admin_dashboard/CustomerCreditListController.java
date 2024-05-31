package group.intelliboys.cimsfrontend.controllers.admin_dashboard;

import group.intelliboys.cimsfrontend.models.customer.Credit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerCreditListController implements Initializable {

    @FXML
    private TableView<Object> creditList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> listOfFieldName = List.of("Id", "ProductName", "Price", "Quantity", "DueDate", "Paid");

        ObservableList<Object> credits = FXCollections.observableArrayList(
                Credit.builder()
                        .id(1)
                        .customerId(5)
                        .paid("NO")
                        .productName("Goldfish")
                        .price(1320)
                        .quantity(2)
                        .createdAt(LocalDate.now())
                        .dueDate(LocalDate.now().plusDays(30))
                        .build(),

                Credit.builder()
                        .id(2)
                        .customerId(5)
                        .paid("NO")
                        .productName("Sinandomeng")
                        .price(1250)
                        .quantity(1)
                        .createdAt(LocalDate.now())
                        .dueDate(LocalDate.now().plusDays(30))
                        .build(),

                Credit.builder()
                        .id(3)
                        .customerId(5)
                        .paid("YES")
                        .productName("Super Angelica")
                        .price(1250)
                        .quantity(1)
                        .createdAt(LocalDate.now().minusDays(30))
                        .dueDate(LocalDate.now().minusDays(3))
                        .build()
        );
        listOfFieldName.forEach(fieldName -> {
            TableColumn<Object, String> fieldColumn = new TableColumn<Object, String>(fieldName);
            fieldColumn.setStyle("-fx-font-size: 18");
            fieldColumn.setCellValueFactory(new PropertyValueFactory<>(fieldName));

            creditList.getColumns().add(fieldColumn);
        });

        creditList.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        creditList.setItems(credits);
    }
}
