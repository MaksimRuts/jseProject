import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.daoImpl.ResultDaoImpl;
import by.gsu.epamlab.database.connection.BaseConnection;
import by.gsu.epamlab.database.managment.BaseManager;
import by.gsu.epamlab.database.managment.BaseManagmentQueries;
import by.gsu.epamlab.parsers.CSVParser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Runner {
    public static final String SRC_TEST1_CSV = "src/task1.csv";

    public static void main(String[] args) {

        BaseManager.clearDatabase();

        CSVParser parser = new CSVParser(SRC_TEST1_CSV);
        ResultDaoImpl resultDao = new ResultDaoImpl();

        while (parser.hasNextResult()) {
            resultDao.create(parser.getResult());
        }


        System.out.println(" All results: ");
        for (Result result : resultDao.get()) {
            System.out.println(result);
        }

        System.out.println("\r\n Mean marks: \r\n" + getMeanMarks());

        List<Result> currentMonthResults = new LinkedList<Result>();
        currentMonthResults.addAll(resultDao.getCurrentMonth());

        System.out.println(" Current month results: ");
        for (Result result : currentMonthResults) {
            System.out.println(result);
        }

        System.out.println("\r\n Latest results: ");
        Result latest = currentMonthResults.get(currentMonthResults.size() - 1);
        for (Result result : currentMonthResults) {
            if (result.getDate().getDay() == latest.getDate().getDay()) {
                System.out.println(result);
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
                results.append(String.format("%.2f", Double.parseDouble(rs.getString("mean"))));
                results.append("\r\n");
            }
        } catch (SQLException e) {
            // todo обработать исключение
            e.printStackTrace();
        }

        return results.toString();
    }
}
