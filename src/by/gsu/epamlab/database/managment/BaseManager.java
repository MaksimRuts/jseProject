package by.gsu.epamlab.database.managment;

import by.gsu.epamlab.database.connection.BaseConnection;

public class BaseManager {
    public static void createDatabase() {
        BaseConnection.update(BaseManagmentQueries.QUERY_CREATE_TESTS);
        BaseConnection.update(BaseManagmentQueries.QUERY_CREATE_LOGINS);
        BaseConnection.update(BaseManagmentQueries.QUERY_CREATE_RESULTS);
    }

    public static void dropDatabase() {
        BaseConnection.update(BaseManagmentQueries.QUERY_DROP_RESULTS);
        BaseConnection.update(BaseManagmentQueries.QUERY_DROP_TESTS);
        BaseConnection.update(BaseManagmentQueries.QUERY_DROP_LOGINS);
    }

    public static void clearDatabase() {
        dropDatabase();
        createDatabase();
    }
}
