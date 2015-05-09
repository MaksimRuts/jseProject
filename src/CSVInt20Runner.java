import by.gsu.epamlab.beans.AbstractResult;
import by.gsu.epamlab.beans.CSVInt20Result;
import by.gsu.epamlab.daoImpl.ResultDaoImpl;
import by.gsu.epamlab.database.connection.BaseConnection;
import by.gsu.epamlab.database.managment.BaseManager;
import by.gsu.epamlab.database.managment.BaseManagmentQueries;
import by.gsu.epamlab.parsers.CSVParser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CSVInt20Runner {
    public static final String SRC_TASK3_CSV = "src/task3.csv";

    public static void main(String[] args) {
        System.out.println("Task 3 (csv)\r\n");

        BaseManager.clearDatabase();
        AbstractResult resultInstance = new CSVInt20Result();

        CSVParser parser = new CSVParser(resultInstance, SRC_TASK3_CSV);
        ResultDaoImpl resultDao = new ResultDaoImpl(resultInstance);

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
            AbstractResult latest = currentMonthResults.get(currentMonthResults.size() - 1);
            for (AbstractResult result : currentMonthResults) {
                if (result.getDate().getDay() == latest.getDate().getDay()) {
                    System.out.println(result);
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
                results.append(String.format("%.2f", Double.parseDouble(rs.getString("mean")) / 5));
                results.append("\r\n");
            }
        } catch (SQLException e) {
            // todo обработать исключение
            e.printStackTrace();
        }

        return results.toString();
    }
}
