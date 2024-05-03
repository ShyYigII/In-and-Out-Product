package controller.product;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.ImportBill;
import model.Product;

import java.net.URL;
import java.util.ResourceBundle;


public class product_InfoController implements Initializable {
    @FXML
    private Label describeLabel;
    @FXML
    private Label nameLable;
    @FXML
    private Label quantityLabel;
    @FXML
    private ImageView image;

    public void setInfo(Product product){
        System.out.println(product.getName());
        nameLable.setText(product.getName());
        describeLabel.setText(product.getDescription());
        quantityLabel.setText(String.format("%d", product.getQuantity()));
        Image imagezz = new Image(getClass().getResourceAsStream(product.getImgSrc()));
        image.setImage(imagezz);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
