package controller.exportBill;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class loginScreen {
    public Scene setScene() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/mainScreen/mainScreen.fxml"));
        Scene scene = new Scene(root, 500, 400);

        return scene;
    }


    public void onHelloButtonClick(){
        System.out.println("abc");
    }

}
