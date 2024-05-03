package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class exportProductController {
    @FXML private Button btnCancel;
    @FXML private Button btnBackToMainScreen;
    @FXML private Button btncreateExportBill;
    public void backToMainScreen() throws IOException {
            switchSceneController s = new switchSceneController();
            s.backToMainScreen(btnBackToMainScreen);
//        Parent root = FXMLLoader.load(getClass().getResource("/view/mainScreen.fxml"));
//        Scene sceneImport = new Scene(root, 800,600);
//        Stage window = (Stage) btnBackToMainScreen.getScene().getWindow();
//        window.setScene(sceneImport);
//        window.show();
    }

    public void createImportBill() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/exportBill/createExportBill.fxml"));
        Scene sceneImport = new Scene(root, 800,600);
        Stage window = (Stage) btncreateExportBill.getScene().getWindow();
        window.setScene(sceneImport);
        window.show();
    }



    public void btnCancelHandler() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/exportBill/exportProducts.fxml"));
        Scene sceneImport = new Scene(root, 800,600);
        Stage window = (Stage) btnCancel.getScene().getWindow();
        window.setScene(sceneImport);
        window.show();
    }

}
