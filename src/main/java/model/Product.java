package model;

import java.util.ArrayList;

public class Product  {
    private String name;
    private ArrayList<ImportBill> importBill;
    private int quantity;
    private String description;
    private int id;
    private String imgSrc;

    public Product() {
    }

    public Product(String name, ArrayList<ImportBill> importBill, int quantity) {
        this.name = name;
        this.importBill = importBill;
        this.quantity = quantity;
        this.description =description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<ImportBill> getImportBill() {
        return importBill;
    }

    public void setImportBill(ArrayList<ImportBill> importBill) {
        this.importBill = importBill;
    }

    public int getQuantity() {
        return quantity;
    }

}


