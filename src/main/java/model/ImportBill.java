package model;

public class ImportBill {
    private String name;
    private String supplier;
    private int quantity;
    private int price;

    private String date;
    private String describe;

    public ImportBill(String name, String supplier, int quantity, int price, String date, String describe) {
        this.name = name;
        this.supplier = supplier;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
        this.describe = describe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
