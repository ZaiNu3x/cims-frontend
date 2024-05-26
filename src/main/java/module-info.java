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
    opens group.intelliboys.cimsfrontend.controllers.login_registration to javafx.fxml;
    opens group.intelliboys.cimsfrontend.controllers.admin_dashboard to javafx.fxml;
    opens group.intelliboys.cimsfrontend.models.customer to javafx.base;
    exports group.intelliboys.cimsfrontend;
    exports group.intelliboys.cimsfrontend.controllers.login_registration;
    exports group.intelliboys.cimsfrontend.controllers.admin_dashboard;
    exports group.intelliboys.cimsfrontend.models.user to com.google.gson;
}