package by.gsu.epamlab.daoImpl;

import by.gsu.epamlab.beans.Login;
import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.beans.Test;
import by.gsu.epamlab.dao.ResultDao;
import by.gsu.epamlab.database.connection.BaseConnection;
import by.gsu.epamlab.database.managment.BaseManagmentQueries;
import by.gsu.epamlab.database.managment.ResultsFields;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ResultDaoImpl implements ResultDao {
    private ResultBasePattern resultDB;
    private PreparedStatement preparedStatement;

    @Override
    public void create(Result result) {
        resultDB = new ResultBasePattern();

        Login login = new Login(result.getLogin());
        LoginDaoImpl loginDao = new LoginDaoImpl();
        resultDB.setLoginId(loginDao.create(login));

        Test test = new Test(result.getTest());
        TestDaoImpl testDao = new TestDaoImpl();
        resultDB.setTestId(testDao.create(test));

        resultDB.setDate(result.getDate());
        resultDB.setMark(result.getMark());

        try {
            preparedStatement = BaseConnection.get().prepareStatement(BaseManagmentQueries.PREPARE_INSERT_TO_RESULTS);
            preparedStatement.setInt(ResultsFields.LOGINID.ordinal(), resultDB.getLoginId());
            preparedStatement.setInt(ResultsFields.TESTID.ordinal(), resultDB.getTestId());
            preparedStatement.setDate(ResultsFields.DATA.ordinal(), resultDB.getDate());
            preparedStatement.setInt(ResultsFields.MARK.ordinal(), resultDB.getMark());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // TODO обработать исключение
            e.printStackTrace();
        }
    }

    @Override
    public List<Result> get() {
        return getResults(BaseManagmentQueries.PREPARE_SELECT_FROM_RESULTS);
    }

    @Override
    public List<Result> getCurrentMonth() {

        return getResults(BaseManagmentQueries.PREPARE_SELECT_FROM_RESULTS_FOR_MONTH);
    }

    private List<Result> getResults(String query) {
        List<Result> results = new LinkedList<Result>();

        try {
            ResultSet rs;
            preparedStatement = BaseConnection.get().prepareStatement(query);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Result result = new Result();
                result.setLogin(rs.getString("login"));
                result.setTest(rs.getString("test"));
                result.setDate(rs.getDate("data"));
                result.setMark(rs.getInt("mark"));
                results.add(result);
            }

        } catch (SQLException e) {
            // TODO обработать исключение
            e.printStackTrace();
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