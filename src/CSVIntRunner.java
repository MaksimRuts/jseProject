import by.gsu.epamlab.beans.AbstractResult;
import by.gsu.epamlab.beans.CSVIntResult;
import by.gsu.epamlab.dao.ResultDao;
import by.gsu.epamlab.daoImpl.ResultDaoImpl;
import by.gsu.epamlab.database.managment.BaseManager;
import by.gsu.epamlab.parsers.CSVParser;

public class CSVIntRunner {
    public static final String SRC_TASK1_CSV = "src/task1.csv";

    public static void main(String[] args) {
        System.out.println("Task 1 (csv)\r\n");

        BaseManager.clearDatabase();
        AbstractResult resultInstance = new CSVIntResult();

        CSVParser parser = new CSVParser(resultInstance, SRC_TASK1_CSV);
        ResultDao resultDao = new ResultDaoImpl(resultInstance);

        RunnerLogic.doSomething(parser, resultDao);
    }
}