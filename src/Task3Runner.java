import by.gsu.epamlab.daoImpl.ResultDaoImpl;
import by.gsu.epamlab.factories.HalfResultFactory;
import by.gsu.epamlab.factories.ResultFactory;
import by.gsu.epamlab.parsers.CSVParser;
import exceptions.ParseException;

public class Task3Runner {
    public static final String SRC_TASK3_CSV = "src/task3.csv";

    public static void main(String[] args) {
        System.out.println("Task 3 (csv)\r\n");

        try {
            ResultFactory resultFactory = new HalfResultFactory();
            CSVParser parser = new CSVParser(resultFactory, SRC_TASK3_CSV);
            ResultDaoImpl resultDao = new ResultDaoImpl(resultFactory);
            RunnerLogic.doSomething(parser, resultDao);
        } catch (ParseException e) {
            System.err.println(e);
        }
    }
}
