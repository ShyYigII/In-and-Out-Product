package app;

import controller.mainScreenController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        mainScreenController mainscreen = new mainScreenController();
        Scene scene = mainscreen.initial();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Quản lý xuất nhập hàng");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
