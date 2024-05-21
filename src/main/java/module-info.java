module group.intelliboys.cimsfrontend {
    requires javafx.controls;
    requires javafx.fxml;


    opens group.intelliboys.cimsfrontend to javafx.fxml;
    exports group.intelliboys.cimsfrontend;
    exports group.intelliboys.cimsfrontend.controllers;
    opens group.intelliboys.cimsfrontend.controllers to javafx.fxml;
}