package controller.importBill;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import model.ImportBill;

import java.net.URL;
import java.util.ResourceBundle;



public class importBill_InfoController implements Initializable {
    @FXML
    private Label dateLabel;

    @FXML
    private Label describeLabel;

    @FXML
    private Label nameLable;

    @FXML
    private Label priceLabel;

    @FXML
    private Label quantityLabel;

    @FXML
    private Label supplierLabel;


    public void setInfo(ImportBill importBill){
        nameLable.setText(importBill.getName());
        dateLabel.setText(importBill.getDate());
        describeLabel.setText(importBill.getDescription());
        priceLabel.setText(String.format("%d", importBill.getPrice()));
        quantityLabel.setText(String.format("%d", importBill.getQuantity()));
        supplierLabel.setText(importBill.getSupplier());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
