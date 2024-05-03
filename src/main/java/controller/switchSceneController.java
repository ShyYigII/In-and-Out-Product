package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class switchSceneController {
   public static switchSceneController getInstance() {
       return new switchSceneController();
   }

    public void backToMainScreen(Button b) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/mainScreen/mainScreen.fxml"));
        Scene sceneImport = new Scene(root);
        Stage window = (Stage) b.getScene().getWindow();
        window.setScene(sceneImport);
        window.show();
    }

    public void createImportBill(Button b) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/importBill/createImportBill.fxml"));
        Scene sceneImport = new Scene(root);
        Stage window = (Stage) b.getScene().getWindow();
        window.setScene(sceneImport);
        window.show();
    }

    public void findImportBill(Button b) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/importBill/changeImportBill.fxml"));
        Scene sceneImport = new Scene(root);
        Stage window = (Stage) b.getScene().getWindow();
        window.setScene(sceneImport);
        window.show();
    }

    public void ImportProductMain(Button b) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/importBill/importProducts.fxml"));
        Scene sceneImport = new Scene(root);
        Stage window = (Stage) b.getScene().getWindow();
        window.setScene(sceneImport);
        window.show();
    }

    public void CancelCreateBill(Button b) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/importBill/importProducts.fxml"));
        Scene sceneImport = new Scene(root);
        Stage window = (Stage) b.getScene().getWindow();
        window.setScene(sceneImport);
        window.show();
    }

    public void deleteImportBill(Button b) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/importBill/deleteImportBill.fxml"));
        Scene sceneImport = new Scene(root, 800,600);
        Stage window = (Stage) b.getScene().getWindow();
        window.setScene(sceneImport);
        window.show();
    }

}
