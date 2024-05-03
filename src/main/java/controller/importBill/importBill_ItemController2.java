package controller.importBill;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import javafx.stage.Stage;
import model.ImportBill;

public class importBill_ItemController2 implements Initializable {
    @FXML
    public Button FixButton;
    @FXML
    private Button  seeInfoButton;
    @FXML
    private Label ImportBillName, Date;
    @FXML
    private HBox around;
    private ImportBill importBill;
//    private importBillListController2 i;

    @FXML
    void click2(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/importBill/importBill_Info.fxml"));
        Parent root = loader.load();
        importBill_InfoController controller = loader.getController();
        controller.setInfo(importBill);

        Scene sceneImport = new Scene(root);
        Stage window = new Stage();
        window.setScene(sceneImport);
        window.setX(150);
        window.setY(100);
        window.show();


    }

    @FXML
    void click(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/importBill/importBillChangeInfo.fxml"));
        Parent root = loader.load();
        ChangeimportProductController controller = loader.getController();
        controller.setInfo(importBill);

        Scene sceneImport = new Scene(root);
        Stage window = new Stage();
        window.setScene(sceneImport);
        window.setX(150);
        window.setY(100);
        window.show();



    }


    public void setData(ImportBill importBill, importBillListController2 controller){
        this.importBill = importBill;
//        this.i =controller;
        ImportBillName.setText( importBill.getName() );
        Date.setText("Nhập ngày: " + importBill.getDate());
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
