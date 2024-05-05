package controller.product;

import dao.productDAO;
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
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.ResourceBundle;

public class productListController implements Initializable {

    @FXML
    private VBox ProductsLayout;
    @FXML
    Button btnCancel2, btnFind;
    @FXML
    TextField findByNameTextField;
    private ArrayList<Product> products = new ArrayList<>();
    public productListController getInstance() {
       return this;
    }
    @FXML
    private ChoiceBox<String> choicebox;
    private String[] ChoiceList = {"Chữ cái", "Số lượng"};
    private boolean check = false;

    private void howToFind(ArrayList<Product> ProductList){
        ProductsLayout.getChildren().clear();

        try {
            for (int i = 0; i < ProductList.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/product/Product_Item.fxml"));
                HBox hbox = fxmlLoader.load();
                product_ItemController ii = fxmlLoader.getController();

                ii.setData(ProductList.get(i));

                ProductsLayout.getChildren().add(hbox);

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
        products = new ArrayList<>();
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
            products.addAll(productDAO.getInstance().selectAll());

        }else{
            ArrayList<Product> a = productDAO.getInstance().selectByName(s);
            products.addAll(a);
        }
        if(!check){
            Collections.sort(products, (o1, o2) -> {return o1.getName().compareTo(o2.getName());});
            howToFind(products);}
        else{
            Collections.sort(products, (o1, o2) -> {return -o1.getQuantity() + o2.getQuantity();});
            howToFind(products);
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Product> ProductList = products;
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
                    howToFind(products);

                });

        }


    public void CancelCreateBill2() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/mainScreen/mainScreen.fxml"));
        Scene sceneImport = new Scene(root);
        Stage window = (Stage) btnCancel2.getScene().getWindow();
        window.setScene(sceneImport);
        window.show();
    }

    public void removeHBoxFromLayout(HBox hbox) {
        ProductsLayout.getChildren().remove(hbox);
    }


}
