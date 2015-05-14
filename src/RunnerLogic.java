import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.dao.ResultDao;
import by.gsu.epamlab.database.connection.BaseConnection;
import by.gsu.epamlab.database.managment.BaseManager;
import by.gsu.epamlab.database.managment.BaseManagmentQueries;
import by.gsu.epamlab.parsers.AbstractParser;
import exceptions.DataBaseException;
import exceptions.ParseException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class RunnerLogic {
    public static void doSomething(AbstractParser parser, ResultDao resultDao) {
        try {
            BaseManager.clearDatabase();
        } catch (DataBaseException e) {
            System.err.println(e.toString());
            return;
        }


        try {
            while (parser.hasNextResult()) {
                resultDao.create(parser.nextResult());
            }

            List<Result> results = new LinkedList<Result>();
            results.addAll(resultDao.get());
            if (results.size() > 0) {
                System.out.println(" All results: ");
                for (Result result : results) {
                    System.out.println(result);
                }

                System.out.println("\r\n Mean marks: \r\n" + getMeanMarks());
                List<Result> currentMonthResults = new LinkedList<Result>();
                try {
                    currentMonthResults.addAll(resultDao.getCurrentMonth());
                } catch (DataBaseException e) {
                    System.out.println("none");
                }

                System.out.println(" Current month results: ");
                if (currentMonthResults.size() > 0) {
                    for (Result result : currentMonthResults) {
                        System.out.println(result);
                    }
                } else {
                    System.out.println("none");
                }

                ListIterator listIterator = currentMonthResults.listIterator();
                if (listIterator.hasNext()) {
                    System.out.println("\r\n Latest results: ");
                    while (listIterator.hasNext()) {
                        listIterator.next();
                    }

                    Result latest = (Result) listIterator.previous();
                    listIterator.next();

                    while (listIterator.hasPrevious()) {
                        Result result = (Result) listIterator.previous();
                        if (result.getDate().getDay() == latest.getDate().getDay()) {
                            System.out.println(result);
                        } else {
                            break;
                        }
                    }
                }
            }
        } catch (DataBaseException e) {
            System.err.println(e);
        } finally {
            parser.close();
            BaseConnection.closeConnection();
        }
    }

    private static String getMeanMarks() {
        StringBuilder results = new StringBuilder();
        ResultSet rs = null;
        try {
            rs = BaseConnection.query(BaseManagmentQueries.QUERY_SELECT_MEAN_MARK);
            while (rs.next()) {
                results.append(rs.getString("login"));
                results.append(";");
                results.append(String.format("%.2f", Double.parseDouble(rs.getString("mean")) / 10));
                results.append("\r\n");
            }
        } catch (SQLException e) {
            throw new DataBaseException(BaseManagmentQueries.QUERY_SELECT_MEAN_MARK, e);
        } catch (NumberFormatException e) {
            throw new ParseException(e);
        } finally {
            BaseConnection.closeResultSet(rs);
            BaseConnection.closeStatement();
        }

        return results.toString();
    }
}
