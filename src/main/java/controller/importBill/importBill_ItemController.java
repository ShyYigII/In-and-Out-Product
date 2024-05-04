package controller.importBill;

import controller.product.productListController;
import dao.importProductBillDAO;
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

public class importBill_ItemController implements Initializable {

    @FXML
    private Button DeleteButton, seeInfoButton;
    @FXML
    private Label ImportBillName, Date;
    @FXML
    private HBox around;
    private ImportBill importBill;
    private importBillListController i;
    @FXML
    void click(ActionEvent event) {
        System.out.println(importBill.getId());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận xóa");
        alert.setHeaderText("Bạn có chắc chắn muốn xóa?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            importProductBillDAO.getInstance().delete(importBill);
        //    DeleteButton.getParent().setVisible(false);
            HBox hbox = (HBox) DeleteButton.getParent();
            i.removeHBoxFromLayout(hbox);
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Thành công!!!");
            alert2.setHeaderText("Bạn đã xóa thành công!");
            alert2.show();

        }


    }

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

    public void setData(ImportBill importBill, importBillListController controller){
        this.importBill = importBill;
        this.i =controller;
        ImportBillName.setText( importBill.getName() );
        Date.setText("Nhập ngày: " + importBill.getDate());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
