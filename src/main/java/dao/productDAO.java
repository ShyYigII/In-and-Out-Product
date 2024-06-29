package dao;

import model.ImportBill;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class productDAO  implements DAOInterface<Product>{
    public  static productDAO getInstance(){ return new productDAO();}
    @Override
    public int insert(Product o) {
      return 0;
    }

    @Override
    public int delete(Product o) {
        return 0;
    }

    @Override
    public int update(Product o) {
           String sql = "UPDATE `testdb`.`product`  SET quantity = quantity + ?  WHERE name = ?";
           try (Connection con = JDBC.getConnection();
                PreparedStatement statement = con.prepareStatement(sql);) {
               statement.setInt(1, o.getQuantity());
               statement.setString(2, o.getName());
               int rowsAffected = statement.executeUpdate();
               System.out.println("Rows effected: " + rowsAffected);
               return rowsAffected;

           } catch (SQLException e) {
               throw new RuntimeException(e);
           }
    }




    @Override
    public ArrayList<Product> selectAll() {
        String sql = "SELECT  * FROM `testdb`.`product`" ;
        ArrayList<Product> products = new ArrayList<>();

        try (Connection con = JDBC.getConnection();
             PreparedStatement statement = con.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getString("name"),
                        new ArrayList<ImportBill>(),
                        resultSet.getInt("quantity"));
                product.setId(resultSet.getInt("id"));
                product.setDescription(resultSet.getString("description"));
                product.setImgSrc("/img/Products/"+ product.getId()+ ".jpg");
                products.add(product);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return products;
    }

    @Override
    public ArrayList<Product> selectByName(String s) {
        String sql = String.format("SELECT * FROM `testdb`.`product` WHERE name = '%s'", s);
        ArrayList<Product> products = new ArrayList<>();

        try (Connection con = JDBC.getConnection();
             PreparedStatement statement = con.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getString("name"),
                        new ArrayList<ImportBill>(),
                        resultSet.getInt("quantity"));

                product.setId(resultSet.getInt("id"));
                product.setDescription(resultSet.getString("description"));
                product.setImgSrc("/img/Products/"+ product.getId()+ ".jpg");
                products.add(product);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return products;

    }

    @Override
    public Product selectById(int i) {
        return null;
    }
}
