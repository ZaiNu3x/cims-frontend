module group.intelliboys.cimsfrontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;
    requires com.nimbusds.jose.jwt;
    requires lombok;
    requires java.desktop;
    requires javafx.swing;
    requires java.base;

    opens group.intelliboys.cimsfrontend to javafx.fxml;
    opens group.intelliboys.cimsfrontend.models to com.google.gson;
    opens group.intelliboys.cimsfrontend.models.user to com.google.gson;
    exports group.intelliboys.cimsfrontend;
    exports group.intelliboys.cimsfrontend.controllers;
    exports group.intelliboys.cimsfrontend.models.user to com.google.gson;
    opens group.intelliboys.cimsfrontend.controllers to javafx.fxml;
}