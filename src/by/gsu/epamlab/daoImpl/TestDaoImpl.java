package by.gsu.epamlab.daoImpl;

import by.gsu.epamlab.beans.Test;
import by.gsu.epamlab.dao.TestDao;
import by.gsu.epamlab.database.connection.BaseConnection;
import by.gsu.epamlab.database.managment.BaseManagmentQueries;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDaoImpl implements TestDao {
    private PreparedStatement preparedStatement;

    @Override
    public int create(Test test) {
        int result = 0;
        try {
            preparedStatement = BaseConnection.get().prepareStatement(BaseManagmentQueries.PREPARE_INSERT_NAMES_TO_TESTS);
            preparedStatement.setString(1, test.getName());
            try {
                preparedStatement.executeUpdate();
            } catch (MySQLIntegrityConstraintViolationException e) {
                // если выскочило исклюение - значит такая запись уже есть в базе, обработка не нужна
            } finally {
                result = get(test.getName()).getTestId();
            }
        } catch (SQLException e) {
            // TODO обработать исключение
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Test get(String name) {
        Test test = null;
        try {
            preparedStatement = BaseConnection.get().prepareStatement(BaseManagmentQueries.PREPARE_SELECT_NAMES_FROM_TESTS);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int id = resultSet.getInt("idTest");
            String _name = resultSet.getString("name");

            test = new Test(id, _name);
        } catch (SQLException e) {
            // TODO обработать исключение
            e.printStackTrace();
        }
        return test;
    }
}
