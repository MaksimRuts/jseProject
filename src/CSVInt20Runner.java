import by.gsu.epamlab.beans.AbstractResult;
import by.gsu.epamlab.beans.CSVInt20Result;
import by.gsu.epamlab.daoImpl.ResultDaoImpl;
import by.gsu.epamlab.database.managment.BaseManager;
import by.gsu.epamlab.parsers.CSVParser;

public class CSVInt20Runner {
    public static final String SRC_TASK3_CSV = "src/task3.csv";

    public static void main(String[] args) {
        System.out.println("Task 3 (csv)\r\n");

        BaseManager.clearDatabase();
        AbstractResult resultInstance = new CSVInt20Result();

        CSVParser parser = new CSVParser(resultInstance, SRC_TASK3_CSV);
        ResultDaoImpl resultDao = new ResultDaoImpl(resultInstance);

        RunnerLogic.doSomething(parser, resultDao);
    }
}
