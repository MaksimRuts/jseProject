package by.gsu.epamlab.daoImpl;

import by.gsu.epamlab.beans.Login;
import by.gsu.epamlab.dao.LoginDao;
import by.gsu.epamlab.database.connection.BaseConnection;
import by.gsu.epamlab.database.managment.BaseManagmentQueries;
import exceptions.DataBaseException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class LoginDaoImpl implements LoginDao {
    public LoginDaoImpl() {
    }

    @Override
    public int create(Login login) {
        PreparedStatement preparedStatement = null;
        int id = 0;
        try {
            preparedStatement = BaseConnection.getPreparedStatement(
                    BaseManagmentQueries.PREPARE_INSERT_NAMES_TO_LOGINS);
            preparedStatement.setString(1, login.getName());
            preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            // name is in the database. do nothing
        } catch (SQLException e) {
            throw new DataBaseException("Create login \'" + login.getName() + "\'", e);
        } finally {
            id = get(login.getName()).getLoginId();
            BaseConnection.closePreparedStatement(preparedStatement);
        }

        return id;
    }

    @Override
    public Login get(String name) {
        Login login = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = BaseConnection.getPreparedStatement(
                    BaseManagmentQueries.PREPARE_SELECT_NAMES_FROM_LOGINS);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int id = resultSet.getInt("idLogin");
            String _name = resultSet.getString("name");

            login = new Login(id, _name);
        } catch (SQLException e) {
            throw new DataBaseException("Get login \'" + name + "\'", e);
        } finally {
            BaseConnection.closeResultSet(resultSet);
            BaseConnection.closePreparedStatement(preparedStatement);
        }
        return login;
    }
}
