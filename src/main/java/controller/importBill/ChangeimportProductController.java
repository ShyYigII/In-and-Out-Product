package controller.importBill;

import dao.importProductBillDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.ImportBill;
import service.updateDataHandler;
import java.time.LocalDate;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ChangeimportProductController  implements Initializable {
    @FXML
    private Button btnCancel;
    @FXML
    private DatePicker dateLabel;

    @FXML
    private TextArea describeLabel;

    @FXML
    private TextField priceLabel;

    @FXML
    private TextField supplierLabel;
    private int id;

    private importBillListController2 i;
    public void setInfo(ImportBill importBill, importBillListController2 i){
        this.id = importBill.getId();
        describeLabel.setText(importBill.getDescription());
        priceLabel.setText(String.format("%d", importBill.getPrice()));
        supplierLabel.setText(importBill.getSupplier());
        this.i = i;
        String dateString = importBill.getDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate importDate = LocalDate.parse(dateString, formatter);
        dateLabel.setValue(importDate);

    }

    public void CancelCreateBill() throws IOException {
        Stage window = (Stage) btnCancel.getScene().getWindow(); // Lấy Stage của cửa sổ hiện tại
        window.close(); // Đóng cửa sổ hiện tại
    }

    @FXML
    private void allertImportBill(int n){
        if(n > 0){
            Alert alertCreateImportBillSuccessfully = new Alert(Alert.AlertType.INFORMATION);
            alertCreateImportBillSuccessfully.setTitle("Sửa phiếu nhập hàng thành công!!!");
            alertCreateImportBillSuccessfully.setHeaderText("Bạn đã sửa phiếu nhập hàng thành công");
            alertCreateImportBillSuccessfully.showAndWait();

        }
        else {
            Alert alertCreateImportBillUnSuccessfully = new Alert(Alert.AlertType.INFORMATION);
            alertCreateImportBillUnSuccessfully.setTitle("Sửa phiếu nhập hàng thất bại!!!");
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void ChangeImportBillHandler() throws IOException, SQLException {
        String supplierName = supplierLabel.getText().trim();
        int price = -1;
        String dateImport = "";

        try{
            price = Integer.parseInt(priceLabel.getText().trim());
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
            dateImport = dateLabel.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }catch (Exception e){
        }
        String description = describeLabel.getText();

        if(  supplierName.isEmpty() || dateImport.isEmpty()) {
            allertImportBill(0);
        }
        else{
            System.out.println(this.id);
            System.out.println(supplierName);
            ImportBill x =  importProductBillDAO.getInstance().selectById(this.id);
            x.setDate(dateImport);
            x.setDescription(description);
            x.setSupplier(supplierName);
            x.setPrice(price);
            int row =  updateDataHandler.getInstance().changeImportBill(x);
            if (row == 1) {
                allertImportBill(1);
                i.refreshData();
                CancelCreateBill();

            }
        }

    }
}
