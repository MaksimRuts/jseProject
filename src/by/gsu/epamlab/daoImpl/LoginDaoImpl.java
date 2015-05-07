package by.gsu.epamlab.daoImpl;

import by.gsu.epamlab.beans.Login;
import by.gsu.epamlab.dao.LoginDao;
import by.gsu.epamlab.database.connection.BaseConnection;
import by.gsu.epamlab.database.managment.BaseManagmentQueries;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDaoImpl implements LoginDao {
    private static final String PREPARE_INSERT_LOGIN = "INSERT INTO logins (name) VALUES (?);";

    private PreparedStatement preparedStatement;

    public LoginDaoImpl() {
    }

    @Override
    public int create(Login login) {
        int id = 0;
        try {
            preparedStatement = BaseConnection.get().prepareStatement(BaseManagmentQueries.PREPARE_INSERT_NAMES_TO_LOGINS);
            preparedStatement.setString(1, login.getName());
            try {
                preparedStatement.executeUpdate();
            } catch (MySQLIntegrityConstraintViolationException e) {
                // если выскочило исклюение - значит такая запись уже есть в базе, обработка не нужна
            } finally {
                id = get(login.getName()).getLoginId();
            }
        } catch (SQLException e) {
            // TODO обработать исключение
            e.printStackTrace();
        }

        return id;
    }

    @Override
    public Login get(String name) {
        Login login = null;
        try {
            preparedStatement = BaseConnection.get().prepareStatement(BaseManagmentQueries.PREPARE_SELECT_NAMES_FROM_LOGINS);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int id = resultSet.getInt("idLogin");
            String _name = resultSet.getString("name");

            login = new Login(id, _name);
        } catch (SQLException e) {
            // TODO обработать исключение
            e.printStackTrace();
        }
        return login;
    }
}
