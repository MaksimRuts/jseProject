package by.gsu.epamlab.daoImpl;

import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.dao.ResultDao;
import by.gsu.epamlab.database.connection.BaseConnection;

import java.sql.SQLException;
import java.sql.Statement;

public class AbstractResultDaoImpl implements ResultDao {
    private Statement statement;

    {
        try {
            statement = BaseConnection.get().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int create(Result result) {
        return 0;
    }

    @Override
    public Result get(int id) {
        return null;
    }
}
