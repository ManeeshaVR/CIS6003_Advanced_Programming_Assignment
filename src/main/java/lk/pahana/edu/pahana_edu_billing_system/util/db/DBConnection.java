package lk.pahana.edu.pahana_edu_billing_system.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/pahana_edu";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private static DBConnection dbConnection;

    private DBConnection() {}

    public static DBConnection getInstance(){
        if(dbConnection == null) {
            return dbConnection = new DBConnection();
        } else {
            return dbConnection;
        }
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }
}
