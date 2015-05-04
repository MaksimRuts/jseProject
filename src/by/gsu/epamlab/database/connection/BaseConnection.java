package by.gsu.epamlab.database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseConnection {
    private static final String DRIVER_URL = "com.mysql.jdbc.Driver";
    private static final String BASE_URL = "jdbc:mysql://localhost/results";
    private static final String BASE_LOGIN = "jse";
    private static final String BASE_PASSWORD = "jse";

    private static Connection connection = null;

    private BaseConnection(){

    }

    static {
        try {
            Class.forName(DRIVER_URL);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver loading error!!");
        }
    }

    public static Connection get() {
        if (connection == null) {
            try {
                DriverManager.getConnection(BASE_URL, BASE_LOGIN, BASE_PASSWORD);
            } catch (SQLException e) {
                System.err.println("Database connecting error (" + BASE_URL + ")");
            }
        }
        return connection;
    }

    public static void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Connection closing error");
            } finally {
                connection = null;
            }
        }
    }
}
