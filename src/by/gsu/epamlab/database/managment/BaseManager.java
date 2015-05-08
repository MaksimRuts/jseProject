package by.gsu.epamlab.database.managment;

import by.gsu.epamlab.database.connection.BaseConnection;

import java.sql.SQLException;

public class BaseManager {
    public static void createDatabase() {
        try {
            BaseConnection.update(BaseManagmentQueries.QUERY_CREATE_TESTS);
            BaseConnection.update(BaseManagmentQueries.QUERY_CREATE_LOGINS);
            BaseConnection.update(BaseManagmentQueries.QUERY_CREATE_RESULTS);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void dropDatabase() {
        try {
            BaseConnection.update(BaseManagmentQueries.QUERY_DROP_RESULTS);
            BaseConnection.update(BaseManagmentQueries.QUERY_DROP_TESTS);
            BaseConnection.update(BaseManagmentQueries.QUERY_DROP_LOGINS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void clearDatabase(){
        dropDatabase();
        createDatabase();
    }
}
