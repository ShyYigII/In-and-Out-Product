package dao;

import java.sql.*;

public class JDBC {

        public static Connection getConnection() {
            String url = "jdbc:mysql://localhost:3306/testdb";
            String user = "root";
            String password = "987654321q";

            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, user, password);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return connection;

        }

}


