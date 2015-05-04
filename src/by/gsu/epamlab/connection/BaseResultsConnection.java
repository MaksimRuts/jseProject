package by.gsu.epamlab.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseResultsConnection {
    private static Connection connection = null;
    private static final String BASE_URL = "results";
    private static final String BASE_LOGIN = "jse";
    private static final String BASE_PASSWORD = "jse";


    static {

    }

    public static Connection getConnection() {
        if (connection == null) {
            DriverManager.getConnection()
        }
    }
}
