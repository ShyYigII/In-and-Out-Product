package service;

import model.ExportBill;
import model.ImportBill;
import dao.*;
import model.Product;

import java.util.ArrayList;

public class updateDataHandler {
    public static updateDataHandler getInstance(){
        return new updateDataHandler();
    }

    public int crateImportBill(ImportBill importBill, Product product){
        int rowOfBill = importProductBillDAO.getInstance().insert(importBill);
        int rowofProduct =  productDAO.getInstance().update(product);
//        ArrayList<ImportBill> importBillsList = importProductBillDAO.getInstance().selectAll();

        return rowOfBill+ rowofProduct;
   }

   public int changeImportBill(ImportBill importBill) {
       int rowOfBill = importProductBillDAO.getInstance().update(importBill);
        return rowOfBill;
    }

    public int crateExportBill(ExportBill exportBill, Product product){
        int rowOfBill = exportProductBillDAO.getInstance().insert(exportBill);
        int rowofProduct =  productDAO.getInstance().update(product);

//        ArrayList<ImportBill> importBillsList = importProductBillDAO.getInstance().selectAll();

        return rowOfBill+ rowofProduct;
    }


}
