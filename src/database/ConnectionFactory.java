package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    //static reference to itself
    private static ConnectionFactory instance = new ConnectionFactory();
    private static final String URL = "jdbc:mysql://localhost:8889/";
    private static final String DATABASE_NAME = "titodb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    public static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    public static String unicode = "?useUnicode=yes&characterEncoding=UTF-8";
    public static String ssl = "?verifyServerCertificate=false&useSSL=true"; // Fixes SSL error

    //private constructor
    public ConnectionFactory() {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL + DATABASE_NAME, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("ERROR: Unable to Connect to Database.");
        }
        return connection;
    }

    public static Connection getConnection() {
        return instance.createConnection();
    }
}
