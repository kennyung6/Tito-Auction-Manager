package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    //static reference to itself
    private static ConnectionFactory instance = new ConnectionFactory();

//    String url = "jdbc:mysql://localhost:3306/AUCTIONDATABASE";
//    String userName = "root";
//    String password = "";
//    String driver = "com.mysql.jdbc.Driver";



    private static final String URL = "jdbc:mysql://localhost:3306/titodb";
    //private static final String DATABASE_NAME = "titodb";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    public static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    public static String unicode = "?useUnicode=yes&characterEncoding=UTF-8";
    public static String ssl = "?verifyServerCertificate=false&useSSL=true"; // Fixes SSL errortito

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
            connection = DriverManager.getConnection(URL , USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("ERROR: Unable to Connect to Database.");
        }
        return connection;
    }

    public static Connection getConnection() {
        return instance.createConnection();
    }
}
