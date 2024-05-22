module group.intelliboys.cimsfrontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;
    requires com.nimbusds.jose.jwt;
    requires lombok;

    opens group.intelliboys.cimsfrontend to javafx.fxml;
    opens group.intelliboys.cimsfrontend.models to com.google.gson;
    exports group.intelliboys.cimsfrontend;
    exports group.intelliboys.cimsfrontend.controllers;
    opens group.intelliboys.cimsfrontend.controllers to javafx.fxml;
}