package by.gsu.epamlab.daoImpl;

import by.gsu.epamlab.beans.Login;
import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.beans.Test;
import by.gsu.epamlab.dao.LoginDao;
import by.gsu.epamlab.dao.ResultDao;
import by.gsu.epamlab.dao.TestDao;
import by.gsu.epamlab.database.connection.BaseConnection;
import by.gsu.epamlab.database.managment.BaseManagmentQueries;
import by.gsu.epamlab.database.managment.ResultsFields;
import by.gsu.epamlab.factories.ResultFactory;
import exceptions.DataBaseException;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class ResultDaoImpl implements ResultDao {
    private ResultBasePattern resultDB;

    private final ResultFactory factory;

    public ResultDaoImpl(ResultFactory factory) {
        this.factory = factory;
    }

    private Result createInstance() {
        return factory.newInstance();
    }

    @Override
    public void create(Result result) {
        PreparedStatement preparedStatement = null;
        resultDB = new ResultBasePattern();

        Login login = new Login(result.getLogin());
        LoginDao loginDao = new LoginDaoImpl();
        resultDB.setLoginId(loginDao.create(login));

        Test test = new Test(result.getTest());
        TestDao testDao = new TestDaoImpl();
        resultDB.setTestId(testDao.create(test));

        resultDB.setDate(result.getDate());
        resultDB.setMark(result.getMark());

        try {
            preparedStatement = BaseConnection.getPreparedStatement(BaseManagmentQueries.PREPARE_INSERT_TO_RESULTS);
            preparedStatement.setInt(ResultsFields.LOGINID.ordinal(), resultDB.getLoginId());
            preparedStatement.setInt(ResultsFields.TESTID.ordinal(), resultDB.getTestId());
            preparedStatement.setDate(ResultsFields.DATA.ordinal(), resultDB.getDate());
            preparedStatement.setInt(ResultsFields.MARK.ordinal(), resultDB.getMark());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataBaseException("Create result \'" + result + "\'", e);
        } finally {
            BaseConnection.closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public List<Result> get() {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = BaseConnection.getPreparedStatement(BaseManagmentQueries.PREPARE_SELECT_FROM_RESULTS);
            return getResults(preparedStatement);
        } catch (SQLException e) {
            return new LinkedList<Result>();
        } finally {
            BaseConnection.closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public List<Result> getCurrentMonth() {
        PreparedStatement preparedStatement = null;
        try {
            Date date = Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
            preparedStatement = BaseConnection.getPreparedStatement(
                    BaseManagmentQueries.PREPARE_SELECT_FROM_RESULTS_FOR_MONTH);
            date.setDate(1);
            preparedStatement.setDate(1, date);
            date.setDate(31);
            preparedStatement.setDate(2, date);
            return getResults(preparedStatement);
        } catch (SQLException e) {
            return new LinkedList<Result>();
        } finally {
            BaseConnection.closePreparedStatement(preparedStatement);
        }
    }

    private List<Result> getResults(PreparedStatement preparedStatement) throws SQLException {
        List<Result> results = new LinkedList<Result>();

        ResultSet rs = null;
        try {
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Result result = createInstance();
                result.setLogin(rs.getString("login"));
                result.setTest(rs.getString("test"));
                result.setDate(rs.getDate("dat"));
                result.setMark(rs.getInt("mark"));
                results.add(result);
            }
        } finally {
            BaseConnection.closeResultSet(rs);
        }

        return results;
    }

    private class ResultBasePattern {
        private int loginId;
        private int testId;
        private Date date;
        private int mark;

        public ResultBasePattern() {
        }

        public ResultBasePattern(int loginId, int testId, Date date, int mark) {
            this.loginId = loginId;
            this.testId = testId;
            this.date = date;
            this.mark = mark;
        }

        public int getLoginId() {
            return loginId;
        }

        public void setLoginId(int loginId) {
            this.loginId = loginId;
        }

        public int getTestId() {
            return testId;
        }

        public void setTestId(int testId) {
            this.testId = testId;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public int getMark() {
            return mark;
        }

        public void setMark(int mark) {
            this.mark = mark;
        }

        @Override
        public String toString() {
            return "ResultBasePattern{" +
                    "loginId=" + loginId +
                    ", testId=" + testId +
                    ", date=" + date +
                    ", mark=" + mark +
                    '}';
        }
    }
}