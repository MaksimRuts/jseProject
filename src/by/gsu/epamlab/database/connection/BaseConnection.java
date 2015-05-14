package by.gsu.epamlab.database.connection;

import exceptions.DataBaseException;
import exceptions.ExceptionsConstants;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class BaseConnection {
    private static final String DRIVER_URL = "com.mysql.jdbc.Driver";
    private static final String BASE_URL = "jdbc:mysql://localhost/results";
    private static final String BASE_LOGIN = "root";
    private static final String BASE_PASSWORD = "";

    private static Connection connection = null;
    private static Statement statement = null;
    private static Map<String, PreparedStatement> prepStmtPool = new HashMap<String, PreparedStatement>();

    private BaseConnection() {

    }

    static {
        try {
            Class.forName(DRIVER_URL);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver loading error!!");
            System.exit(1);
        }
    }

    public static Connection get() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(BASE_URL, BASE_LOGIN, BASE_PASSWORD);
            } catch (SQLException e) {
                throw new DataBaseException("Database connecting error (" + BASE_URL + ")", e);
            }
        }
        return connection;
    }

    public static PreparedStatement getPreparedStatement(String query) {
        try {
            return get().prepareStatement(query);
        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
    }

    public static ResultSet query(String query) {
        try {
            if (statement == null) {
                statement = get().createStatement();
            }
            return statement.executeQuery(query);
        } catch (SQLException e) {
            throw new DataBaseException("executeQuery: \'" + query + "\'", e);
        }
    }

    public static int update(String query) {
        try {
            if (statement == null) {
                statement = get().createStatement();
            }
            return statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new DataBaseException("executeUpdate: \'" + query + "\'", e);
        }
    }

    public static void closeResultSet(ResultSet... sets) {
        for (ResultSet resultSet : sets) {
            if (resultSet != null) {
                try {
                    if (!resultSet.isClosed()) {
                        resultSet.close();
                    }
                } catch (SQLException e) {
                    throw new DataBaseException(ResultSet.class.getSimpleName() +
                            ExceptionsConstants.OBJECTS_CLOSING, e);
                }
            }
        }
    }

    public static void closePreparedStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new DataBaseException(PreparedStatement.class.getSimpleName() +
                        ExceptionsConstants.OBJECTS_CLOSING, e);
            }
        }
    }

    public static void closeStatement() {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DataBaseException(Statement.class.getSimpleName() +
                        ExceptionsConstants.OBJECTS_CLOSING, e);
            }
        }
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                throw new DataBaseException(Connection.class.getSimpleName() +
                        ExceptionsConstants.OBJECTS_CLOSING, e);
            }
        }
    }
}
