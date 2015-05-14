package by.gsu.epamlab.daoImpl;

import by.gsu.epamlab.beans.Test;
import by.gsu.epamlab.dao.TestDao;
import by.gsu.epamlab.database.connection.BaseConnection;
import by.gsu.epamlab.database.managment.BaseManagmentQueries;
import exceptions.DataBaseException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class TestDaoImpl implements TestDao {

    @Override
    public int create(Test test) {
        PreparedStatement preparedStatement = null;
        int id = 0;
        try {
            preparedStatement = BaseConnection.getPreparedStatement(BaseManagmentQueries.PREPARE_INSERT_NAMES_TO_TESTS);
            preparedStatement.setString(1, test.getName());
            preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            // name is in the database. do nothing
        } catch (SQLException e) {
            throw new DataBaseException("Create test \'" + test.getName() + "\'", e);
        } finally {
            id = get(test.getName()).getTestId();
            BaseConnection.closePreparedStatement(preparedStatement);
        }

        return id;
    }

    @Override
    public Test get(String name) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Test test = null;
        try {
            preparedStatement = BaseConnection.getPreparedStatement(BaseManagmentQueries.PREPARE_SELECT_NAMES_FROM_TESTS);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int id = resultSet.getInt("idTest");
            String _name = resultSet.getString("name");

            test = new Test(id, _name);
        } catch (SQLException e) {
            throw new DataBaseException("Get test \'" + name + "\'", e);
        } finally {
            BaseConnection.closeResultSet(resultSet);
            BaseConnection.closePreparedStatement(preparedStatement);
        }
        return test;
    }
}
