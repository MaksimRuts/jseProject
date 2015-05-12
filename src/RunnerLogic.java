import by.gsu.epamlab.beans.AbstractResult;
import by.gsu.epamlab.dao.ResultDao;
import by.gsu.epamlab.database.connection.BaseConnection;
import by.gsu.epamlab.database.managment.BaseManagmentQueries;
import by.gsu.epamlab.parsers.AbstractParser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class RunnerLogic {
    public static void doSomething(AbstractParser parser, ResultDao resultDao) {

        while (parser.hasNextResult()) {
            resultDao.create(parser.getResult());
        }

        List<AbstractResult> results = new LinkedList<AbstractResult>();
        results.addAll(resultDao.get());
        if (results.size() > 0) {
            System.out.println(" All results: ");
            for (AbstractResult result : results) {
                System.out.println(result);
            }

            System.out.println("\r\n Mean marks: \r\n" + getMeanMarks());

            List<AbstractResult> currentMonthResults = new LinkedList<AbstractResult>();
            currentMonthResults.addAll(resultDao.getCurrentMonth());

            System.out.println(" Current month results: ");
            for (AbstractResult result : currentMonthResults) {
                System.out.println(result);
            }

            System.out.println("\r\n Latest results: ");
//            Collections.reverse(currentMonthResults);
            ListIterator listIterator = currentMonthResults.listIterator();
            while (listIterator.hasNext()) {
                listIterator.next();
            }

            AbstractResult latest = (AbstractResult)listIterator.previous();
            listIterator.next();

            while (listIterator.hasPrevious()) {
                AbstractResult result = (AbstractResult)listIterator.previous();
                if (result.getDate().getDay() == latest.getDate().getDay()) {
                    System.out.println(result);
                } else {
                    break;
                }
            }
        }
    }

    private static String getMeanMarks() {
        StringBuilder results = new StringBuilder();

        try {
            ResultSet rs = BaseConnection.query(BaseManagmentQueries.QUERY_SELECT_MEAN_MARK);
            while (rs.next()) {
                results.append(rs.getString("login"));
                results.append(";");
                results.append(String.format("%.2f", Double.parseDouble(rs.getString("mean")) / 10));
                results.append("\r\n");
            }
        } catch (SQLException e) {
            // todo обработать исключение
            e.printStackTrace();
        }

        return results.toString();
    }
}
