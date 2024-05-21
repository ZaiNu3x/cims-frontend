package group.intelliboys.cimsfrontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {

    public static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Customer's Information Management System");
        stage.setResizable(false);
        stage.getIcons().add(new Image(Objects.requireNonNull(App.class.getResource("images/icon.png")).toString()));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}