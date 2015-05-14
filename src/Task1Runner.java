import by.gsu.epamlab.dao.ResultDao;
import by.gsu.epamlab.daoImpl.ResultDaoImpl;
import by.gsu.epamlab.factories.ResultFactory;
import by.gsu.epamlab.parsers.CSVParser;
import exceptions.ParseException;

public class Task1Runner {
    public static final String SRC_TASK1_CSV = "src/task1.csv";

    public static void main(String[] args) {
        System.out.println("Task 1 (csv)\r\n");

        try {
            ResultFactory resultFactory = new ResultFactory();
            CSVParser parser = new CSVParser(resultFactory, SRC_TASK1_CSV);
            ResultDao resultDao = new ResultDaoImpl(resultFactory);
            RunnerLogic.doSomething(parser, resultDao);
        } catch (ParseException e) {
            System.err.println(e.toString());
        }
    }
}