package by.gsu.epamlab.database.managment;

import by.gsu.epamlab.database.connection.BaseConnection;

import java.sql.SQLException;

public class BaseManager {
    public static void createDatabase() {
        try {
            BaseConnection.update(BaseManagmentQueries.QUERY_CREATE_TABLES);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void dropDatabase() {
        try {
            BaseConnection.update(BaseManagmentQueries.QUERY_DELETE_TABLES);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
