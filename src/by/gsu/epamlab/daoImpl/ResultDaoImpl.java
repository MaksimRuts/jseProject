package by.gsu.epamlab.daoImpl;

import by.gsu.epamlab.beans.Login;
import by.gsu.epamlab.beans.AbstractResult;
import by.gsu.epamlab.beans.Test;
import by.gsu.epamlab.dao.LoginDao;
import by.gsu.epamlab.dao.ResultDao;
import by.gsu.epamlab.dao.TestDao;
import by.gsu.epamlab.database.connection.BaseConnection;
import by.gsu.epamlab.database.managment.BaseManagmentQueries;
import by.gsu.epamlab.database.managment.ResultsFields;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ResultDaoImpl implements ResultDao {
    private ResultBasePattern resultDB;
    private PreparedStatement preparedStatement;

    private final AbstractResult result;

    public <T extends AbstractResult> ResultDaoImpl(T result) {
        this.result = result;
    }

    private <T> AbstractResult getInstance() {
        try {

            Constructor constructor = result.getClass().getConstructor(null);
            return (AbstractResult)constructor.newInstance(null);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void create(AbstractResult result) {
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
    public List<AbstractResult> get() {
        return getResults(BaseManagmentQueries.PREPARE_SELECT_FROM_RESULTS);
    }

    @Override
    public List<AbstractResult> getCurrentMonth() {

        return getResults(BaseManagmentQueries.PREPARE_SELECT_FROM_RESULTS_FOR_MONTH);
    }

    private List<AbstractResult> getResults(String query) {
        List<AbstractResult> results = new LinkedList<AbstractResult>();

        try {
            ResultSet rs;
            preparedStatement = BaseConnection.get().prepareStatement(query);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                AbstractResult result = getInstance();
                // todo create e
                result.setLogin(rs.getString("login"));
                result.setTest(rs.getString("test"));
                result.setDate(rs.getDate("dat"));
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