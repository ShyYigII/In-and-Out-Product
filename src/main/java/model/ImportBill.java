package model;

public class ImportBill {
    private String name;
    private String supplier;
    private int quantity;
    private int price;
    private int id;

    private String date;
    private String description;

    public ImportBill() {
    }

    public ImportBill(String name, String supplier, int quantity, int price, String date, String describe) {
        this.name = name;
        this.supplier = supplier;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
        this.description = describe;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSupplier() {
        return supplier;
    }


    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }


    public String getDescription() {
        return description;
    }

}

