package controller;

import dao.JDBC;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;

public class importProductController {
    @FXML private Button btnCancel;
    @FXML private Button btnBackToMainScreen;
    @FXML private Button btncreateImportBill;
    @FXML private Button btnAddImportBill;


    @FXML private TextField nameTextField;
    @FXML private TextField supplierTextField;
    @FXML private TextField quantityTextField;
    @FXML private TextField priceTextField;
    @FXML private DatePicker date;
    @FXML private TextArea describeTextArea;
    public void backToMainScreen() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/mainScreen.fxml"));
        Scene sceneImport = new Scene(root, 500,500);
        Stage window = (Stage) btnBackToMainScreen.getScene().getWindow();
        window.setScene(sceneImport);
        window.show();
    }

    public void createImportBill() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/createImportBill.fxml"));
        Scene sceneImport = new Scene(root, 500,500);
        Stage window = (Stage) btncreateImportBill.getScene().getWindow();
        window.setScene(sceneImport);
        window.show();
    }



    public void btnCancelHandler() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/importProducts.fxml"));
        Scene sceneImport = new Scene(root, 500,500);
        Stage window = (Stage) btnCancel.getScene().getWindow();
        window.setScene(sceneImport);
        window.show();
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
        alert.show();
    }

//    @FXML
//    private void allertErrorQuantityInvalid(String s){
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle("Lỗi!!!");
//        alert.setHeaderText("Nhập vào "+  s + " phải là 1 số ");
//        alert.setContentText("Hãy nhập lại " + s);
//        alert.show();
//    }

    @FXML
    public void btnAddImportBillHandler() throws IOException, SQLException {
        String productName = nameTextField.getText();
        String supplierName = supplierTextField.getText();
        int quantity = -1;
        int price = -1;
        String dateImport = "";

        try{quantity = Integer.parseInt(quantityTextField.getText());
            if(quantity < 0)  allertErrorParseException("số lượng");

        }
        catch (NumberFormatException e){
            allertErrorParseException("số lượng");
        }
        try{
         price = Integer.parseInt(priceTextField.getText());
         if(price < 0)  allertErrorParseException("giá");
        }
        catch (NumberFormatException e ){
            allertErrorParseException("giá");
        }

        try{
            dateImport = date.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }catch (Exception e){

        }

        String description = describeTextArea.getText();

        if( productName.equals("") || supplierName.equals("") || dateImport.equals("")) {
            allertImportBill(0);
        }
        else{
            Connection con = JDBC.getConConnection();
            try {
                String sql = "INSERT INTO `testdb`.`importbill` (name, supplier, quantity, price, date,description) VALUES (?, ?, ?, ?, ?,?)";
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setString(1, productName);
                statement.setString(2, supplierName);
                statement.setInt(3, quantity);
                statement.setDouble(4, price);
                statement.setString(5, dateImport);
                statement.setString(6, description);
                int rowsAffected = statement.executeUpdate(); // Use executeUpdate for INSERT
                if (rowsAffected > 0) {
                    allertImportBill(1);
                    btnCancelHandler();
                }
                System.out.println(rowsAffected);
            }
            catch (SQLException e){

            }
        }
        }

    }


