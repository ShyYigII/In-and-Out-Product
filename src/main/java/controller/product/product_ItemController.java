package controller.product;

import controller.importBill.importBillListController;
import controller.importBill.importBill_InfoController;
import dao.importProductBillDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.ImportBill;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class product_ItemController implements Initializable {

    @FXML
    private Button DeleteButton, seeInfoButton;
    @FXML
    private Label ProductName;

    private Product product;
    private productListController i;
    @FXML
    private ImageView image;


    @FXML
    void click2(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/product/seeProductInfo.fxml"));
        Parent root = loader.load();

        product_InfoController controller = loader.getController();

        controller.setInfo(product);


        Scene sceneImport = new Scene(root);
        Stage window = new Stage();
        window.setScene(sceneImport);
        window.setX(150);
        window.setY(100);
        window.show();
    }


    public void setData(Product product){
        this.product = product;
        ProductName.setText(product.getName());
        Image imagezz = new Image(getClass().getResourceAsStream(product.getImgSrc()));
        image.setImage(imagezz);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
