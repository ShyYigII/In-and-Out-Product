package controller.importBill;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.ImportBill;
import model.Product;
import service.updateDataHandler;
import controller.switchSceneController;
import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class importProductController {

    @FXML private Button btnCancel, btnDeleteImportBill, btnBackToMainScreen, btncreateImportBill, btnAddImportBill ,btnFindImportBill,btnImportProductMain;
    @FXML private TextField nameTextField, supplierTextField,quantityTextField,priceTextField;
    @FXML private DatePicker date;
    @FXML private TextArea describeTextArea;

    public void backToMainScreen() throws IOException {
            switchSceneController.getInstance().backToMainScreen(btnBackToMainScreen);
    }

    public void createImportBill() throws IOException {
        switchSceneController.getInstance().createImportBill(btncreateImportBill);
    }

    public void findImportBill() throws IOException{
        switchSceneController.getInstance().findImportBill(btncreateImportBill);
    }

    public void ImportProductMain() throws IOException {
        switchSceneController.getInstance().ImportProductMain(btnImportProductMain);
    }

    public void CancelCreateBill() throws IOException {
        switchSceneController.getInstance().CancelCreateBill(btnCancel);
    }


    @FXML
    private void allertImportBill(int n){
        if(n > 0){
            Alert alertCreateImportBillSuccessfully = new Alert(Alert.AlertType.INFORMATION);
            alertCreateImportBillSuccessfully.setTitle("Thêm phiếu nhập hàng thành công!!!");
            alertCreateImportBillSuccessfully.setHeaderText("Bạn đã thêm phiếu nhập hàng thành công");
            alertCreateImportBillSuccessfully.showAndWait();

        }
        else {
            Alert alertCreateImportBillUnSuccessfully = new Alert(Alert.AlertType.INFORMATION);
            alertCreateImportBillUnSuccessfully.setTitle("Thêm phiếu nhập hàng thất bại!!!");
            alertCreateImportBillUnSuccessfully.setHeaderText("Xin hãy điền đủ các trường bắt buộc có dấu (*)");
            alertCreateImportBillUnSuccessfully.show();
        }
    }
    @FXML
    private void allertErrorParseException(String s){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Lỗi!!!");
        alert.setHeaderText("Nhập vào "+  s + " phải là 1 số lớn hơn 0");
        alert.setContentText("Hãy nhập lại " + s);
        alert.showAndWait();
    }



    @FXML
    public void AddImportBillHandler() throws IOException, SQLException {
        String productName = nameTextField.getText().trim();
        String supplierName = supplierTextField.getText().trim();
        int quantity = -1;
        int price = -1;
        String dateImport = "";

        try{quantity = Integer.parseInt(quantityTextField.getText().trim());
            if(quantity < 0) {
                allertErrorParseException("số lượng");
                return;
            }
        }
        catch (NumberFormatException e){
            allertErrorParseException("số lượng");
            return;
        }
        try{
         price = Integer.parseInt(priceTextField.getText().trim());
         if(price < 0)  {
             allertErrorParseException("giá");
            return;
         }
        }
        catch (NumberFormatException e ){
            allertErrorParseException("giá");
            return;
        }
        try{
            dateImport = date.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }catch (Exception e){
        }
        String description = describeTextArea.getText();

        if(productName.isEmpty() || supplierName.isEmpty() || dateImport.isEmpty()) {
            allertImportBill(0);
        }
        else{
            ImportBill importbill = new ImportBill(productName,supplierName,quantity,price,dateImport,description);
            ArrayList<ImportBill> a = new ArrayList<>();
            a.add(importbill);
            Product product = new Product(productName, a ,quantity );

            int row =  updateDataHandler.getInstance().crateImportBill(importbill, product);
                if (row == 2) {
                    allertImportBill(1);
                    CancelCreateBill();
                }
            }

        }

    public void deleteImportBill() throws IOException {
        switchSceneController.getInstance().deleteImportBill(btnFindImportBill);

    }





}


