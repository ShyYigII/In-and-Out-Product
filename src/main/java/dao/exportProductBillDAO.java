package dao;

import model.ExportBill;
import model.ImportBill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class exportProductBillDAO implements DAOInterface<ExportBill>{
    public  static exportProductBillDAO getInstance(){ return new exportProductBillDAO();}

    @Override
    public int insert(ExportBill o) {
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
    public int delete(ExportBill o) {
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
    public int update(ExportBill o) {
        return -1;
    }

    @Override
    public ArrayList<ExportBill> selectAll() {
        String sql = "SELECT  * FROM `testdb`.`importbill`" ;
        ArrayList<ExportBill> exportBills = new ArrayList<>();

        try (Connection con = JDBC.getConnection();
             PreparedStatement statement = con.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                ExportBill exportBill = new ExportBill(
                        resultSet.getString("name"),
                        resultSet.getString("supplier"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("price"),
                        resultSet.getString("date"),
                        resultSet.getString("description"));
                exportBill.setId(resultSet.getInt("id"));
                exportBills.add(exportBill);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return exportBills;
    }

    @Override
    public ArrayList<ExportBill> selectByName(String s) {
        String sql = String.format("SELECT * FROM `testdb`.`importbill` WHERE name = '%s'", s);
        ArrayList<ExportBill> exportBills = new ArrayList<>();

        try (Connection con = JDBC.getConnection();
             PreparedStatement statement = con.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                ExportBill exportBill = new ExportBill(
                        resultSet.getString("name"),
                        resultSet.getString("supplier"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("price"),
                        resultSet.getString("date"),
                        resultSet.getString("description"));
                exportBill.setId(resultSet.getInt("id"));
                exportBills.add(exportBill);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return exportBills;
    }

    @Override
    public ExportBill selectById(int i) {
        return null;
    }


}
