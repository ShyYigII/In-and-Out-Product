package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.ImportBill;

import java.io.IOException;

public class mainScreen {
    @FXML Button btnImport ;
    @FXML Button btnExport;
    @FXML Button btnInfo;
    @FXML Button btnCancel;



    private ImportBill importbill;
    public Scene initial() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/mainScreen.fxml"));
        Scene scene = new Scene(root, 500, 400);

        return scene;
    }
    public void handleImportScene() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/importProducts.fxml"));
        Scene sceneImport = new Scene(root, 500,500);
        Stage window = (Stage) btnImport.getScene().getWindow();
        window.setScene(sceneImport);
    }


    public void handleExportScene() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/exportProducts.fxml"));
        Scene sceneImport = new Scene(root, 500,500);
        Stage window = (Stage) btnExport.getScene().getWindow();
        window.setScene(sceneImport);
    }







}
