package dao;

import model.ImportBill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class importProductBillDAO implements DAOInterface<ImportBill>{
    public  static importProductBillDAO getInstance(){ return new importProductBillDAO();}

    @Override
    public int insert(ImportBill o) {
        String sql = "INSERT INTO `testdb`.`importbill` (name, supplier, quantity, price, date,description) VALUES (?, ?, ?, ?, ?,?)";
        try(Connection con = JDBC.getConnection();
            PreparedStatement statement = con.prepareStatement(sql);) {
            statement.setString(1, o.getName());
            statement.setString(2, o.getSupplier());
            statement.setInt(3, o.getQuantity());
            statement.setDouble(4, o.getPrice());
            statement.setString(5, o.getDate());
            statement.setString(6, o.getDescription());
            int rowsAffected = statement.executeUpdate(); // Use executeUpdate for INSERT
            System.out.println("Rows effected: " + rowsAffected) ;
            return rowsAffected;

        }
        catch (SQLException e){
        throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(ImportBill o) {
        String url = "DELETE  FROM `testdb`.`importbill`  WHERE id = ?";
        // try-with-resources
        try (Connection con = JDBC.getConnection();
             PreparedStatement statement = con.prepareStatement(url)) {

            statement.setString(1, String.format("%d",o.getId() ));
            int rowsAffected = statement.executeUpdate();

            System.out.println("Số dữ liệu được xóa là: " + rowsAffected);
            return rowsAffected;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public int update(ImportBill o) {
        String url = "UPDATE `testdb`.`importbill` SET " +
                "`supplier` = ?, " +
                "`price` = ?, " +
                "`description` = ?, " +
                "`date` = ? " +
                "WHERE `id` = ?";
        // try-with-resources
        try (Connection con = JDBC.getConnection();
             PreparedStatement statement = con.prepareStatement(url)) {
            statement.setString(1,o.getSupplier());
            statement.setString(2,String.format("%d", o.getPrice()));
            statement.setString(3, o.getDescription());
            statement.setString(4,o.getDate());

            statement.setString(5, String.format("%d",o.getId() ));
            int rowsAffected = statement.executeUpdate();

            System.out.println("Số dữ liệu được cập nhật là: " + rowsAffected);
            return rowsAffected;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ArrayList<ImportBill> selectAll() {
        String sql = "SELECT  * FROM `testdb`.`importbill`" ;
        ArrayList<ImportBill> importBills = new ArrayList<>();

        try (Connection con = JDBC.getConnection();
             PreparedStatement statement = con.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                ImportBill importBill = new ImportBill(
                        resultSet.getString("name"),
                        resultSet.getString("supplier"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("price"),
                        resultSet.getString("date"),
                        resultSet.getString("description"));
                importBill.setId(resultSet.getInt("id"));
                importBills.add(importBill);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return importBills;
    }

    @Override
    public ArrayList<ImportBill> selectByName(String s) {
        String sql = String.format("SELECT * FROM `testdb`.`importbill` WHERE name = '%s'", s);
        ArrayList<ImportBill> importBills = new ArrayList<>();

        try (Connection con = JDBC.getConnection();
             PreparedStatement statement = con.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                ImportBill importBill = new ImportBill(
                        resultSet.getString("name"),
                        resultSet.getString("supplier"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("price"),
                        resultSet.getString("date"),
                        resultSet.getString("description"));
                importBill.setId(resultSet.getInt("id"));
                importBills.add(importBill);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return importBills;
    }

    @Override
    public ImportBill selectById(int i) {
        String sql = String.format("SELECT * FROM `testdb`.`importbill` WHERE id = %d", i );
       ImportBill importBill = null;
        try (Connection con = JDBC.getConnection();
             PreparedStatement statement = con.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                 importBill = new ImportBill(
                        resultSet.getString("name"),
                        resultSet.getString("supplier"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("price"),
                        resultSet.getString("date"),
                        resultSet.getString("description"));
                importBill.setId(resultSet.getInt("id"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return importBill;
    }


}
