package controller.exportBill;


import controller.switchSceneController;
import dao.productDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.ExportBill;
import model.ImportBill;
import model.Product;
import service.updateDataHandler;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class exportProductController implements Initializable {

    @FXML
    private Button btnCancel, btnBackToMainScreen, btncreateExportBill ;
    @FXML private TextField nameTextField, supplierTextField,quantityTextField,priceTextField;
    @FXML private DatePicker date;
    @FXML private TextArea describeTextArea;
    private String[] productList = {"Thước kẻ","Bút bi", "Bút chì" , "Bảng con" , "Bút xóa" , "Bút nhớ" , "Tẩy" , "Vở" , "Máy tính" , "Bút màu"};
    private ArrayList<String> productList2 =new ArrayList<>();
    public void backToMainScreen() throws IOException {
        switchSceneController.getInstance().backToMainScreen(btnBackToMainScreen);
    }

    public void createExportBill() throws IOException {
        switchSceneController.getInstance().createExportBill(btncreateExportBill);
    }

    public void CancelCreateExportBill() throws IOException {
        switchSceneController.getInstance().CancelCreateExportBill(btnCancel);
    }

    @FXML
    private void allertImportBill(int n){
        if(n ==1 ){
            Alert alertCreateImportBillSuccessfully = new Alert(Alert.AlertType.INFORMATION);
            alertCreateImportBillSuccessfully.setTitle("Thêm phiếu xuất hàng thành công!!!");
            alertCreateImportBillSuccessfully.setHeaderText("Bạn đã thêm phiếu xuất hàng thành công");
            alertCreateImportBillSuccessfully.showAndWait();

        }
        else if(n == -1){
            Alert alertCreateImportBillUnSuccessfully2 = new Alert(Alert.AlertType.INFORMATION);
            alertCreateImportBillUnSuccessfully2.setTitle("Thêm phiếu xuất hàng thất bại!!!");
            alertCreateImportBillUnSuccessfully2.setHeaderText("Xin hãy điền đủ đúng tên sản phẩm");
            alertCreateImportBillUnSuccessfully2.show();

        }
        else if(n== -2){
            Alert alertCreateImportBillUnSuccessfully3 = new Alert(Alert.AlertType.INFORMATION);
            alertCreateImportBillUnSuccessfully3.setTitle("Thêm phiếu xuất hàng thất bại!!!");
            alertCreateImportBillUnSuccessfully3.setHeaderText("Hàng trong kho không đủ. Hãy nhập lại số lượng.");
            alertCreateImportBillUnSuccessfully3.show();
        }
        else {
            Alert alertCreateImportBillUnSuccessfully = new Alert(Alert.AlertType.INFORMATION);
            alertCreateImportBillUnSuccessfully.setTitle("Thêm phiếu xuất hàng thất bại!!!");
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
    public void AddExportBillHandler() throws IOException, SQLException {
        System.out.println(productList2);
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
        else if(!productList2.contains(productName)){
            allertImportBill(-1);

        }
        else{
            ExportBill exportBill = new ExportBill(productName,supplierName,quantity,price,dateImport,description);
            ArrayList<ImportBill> a = new ArrayList<>();

            Product product = new Product(productName, a ,-quantity );
            ArrayList<Product> p =  productDAO.getInstance().selectByName(productName);
            int productQuantity =0;

            for(Product i : p){
                productQuantity += i.getQuantity();
            }
            if(productQuantity < quantity){
                allertImportBill(-2);
                return;
            }

            int row =  updateDataHandler.getInstance().crateExportBill(exportBill, product);
            if (row == 2) {
                allertImportBill(1);
                CancelCreateExportBill();
            }
        }

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productList2.addAll(List.of(productList));

    }
}
