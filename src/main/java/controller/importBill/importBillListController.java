package controller.importBill;

import dao.importProductBillDAO;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.ImportBill;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class importBillListController implements Initializable {

    @FXML
    private VBox ImportBillsLayout;
    @FXML
    Button btnCancel2, btnFind;
    @FXML
    TextField findByNameTextField;
    @FXML
    private ChoiceBox<String> choicebox;
    private ArrayList<ImportBill>importBills = new ArrayList<>();
    public importBillListController getInstance() {
       return this;
    }

    private boolean check = false;
    private String[] ChoiceList = {"Chữ cái", "Ngày nhập"};


//    private ArrayList<ImportBill> getData(){
//       importBills = importProductBillDAO.getInstance().selectAll();
//        return importBills;
//    }

    private void howToFind(ArrayList<ImportBill> BillList){
        ImportBillsLayout.getChildren().clear();

        try {
            for (int i = 0; i < BillList.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/importBill/ImportBill_Item.fxml"));
                HBox hbox = fxmlLoader.load();
                importBill_ItemController ii = fxmlLoader.getController();
                ii.setData(BillList.get(i), this);

                ImportBillsLayout.getChildren().add(hbox);

            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void allertErrorNameNotExist(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Lỗi!!!");
        alert.setHeaderText("Hàng Không Tồn Tại");
        alert.setContentText("Hãy nhập lại tên" );
        alert.showAndWait();
    }
    @FXML
    private void pleaseEnterName(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Lỗi!!!");
        alert.setHeaderText("Vui lòng nhập tên");
        alert.showAndWait();
    }

    public void findByName(){
        importBills = new ArrayList<>();
        String s = findByNameTextField.getText();
        ArrayList<String> list = new ArrayList<>();
        list.add("Thước kẻ");
        list.add("Bút bi");
        list.add("Bút chì");
        list.add("Bảng con");
        list.add("Bút xóa");
        list.add("Bút nhớ");
        list.add("Tẩy");
        list.add("Vở");
        list.add("Máy tính");
        list.add("Bút màu");

        if(s.isEmpty()){
            pleaseEnterName();
        }
        else if(!list.contains(s) && !s.equals("all")){
            allertErrorNameNotExist();
        }
        else if(s.equals("all")){
            importBills.addAll(importProductBillDAO.getInstance().selectAll());

        }else{
            ArrayList<ImportBill> a = importProductBillDAO.getInstance().selectByName(s);
            importBills.addAll(a);
        }

        if(!check){
            Collections.sort(importBills, (o1, o2) -> {return o1.getName().compareTo(o2.getName());});
            howToFind(importBills);}
        else{

            Collections.sort(importBills, (o1, o2) -> {
                LocalDate date1 = LocalDate.parse(o1.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                LocalDate date2 = LocalDate.parse(o2.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                return date2.compareTo(date1);
            });
            howToFind(importBills);}

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<ImportBill> BillList = importBills;
        choicebox.setValue("Chữ cái");
        choicebox.getItems().addAll(ChoiceList);
        choicebox.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                    if(new_val.equals(1)){
                        check= true;
                        findByName();
                    }
                    else {
                        check = false;
                        findByName();
                    }
                    howToFind(importBills);

                });
        }


    public void CancelCreateBill2() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/importBill/importProducts.fxml"));
        Scene sceneImport = new Scene(root);
        Stage window = (Stage) btnCancel2.getScene().getWindow();
        window.setScene(sceneImport);
        window.show();
    }

    public void removeHBoxFromLayout(HBox hbox) {
        ImportBillsLayout.getChildren().remove(hbox);
    }


}
