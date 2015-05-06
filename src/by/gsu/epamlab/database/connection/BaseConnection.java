package by.gsu.epamlab.database.connection;

import java.sql.*;

public class BaseConnection {
    private static final String DRIVER_URL = "com.mysql.jdbc.Driver";
    private static final String BASE_URL = "jdbc:mysql://localhost/results";
    private static final String BASE_LOGIN = "root";
    private static final String BASE_PASSWORD = "";

    private static Connection connection = null;
    private static Statement statement = null;

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
                connection = DriverManager.getConnection(BASE_URL, BASE_LOGIN, BASE_PASSWORD);
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

    public static ResultSet query(String query) throws SQLException {
        statement = get().createStatement();
        return statement.executeQuery(query);
    }

    public static int update(String query) throws SQLException {
        statement = get().createStatement();
        return statement.executeUpdate(query);
    }
}
