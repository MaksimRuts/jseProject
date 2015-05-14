import by.gsu.epamlab.daoImpl.ResultDaoImpl;
import by.gsu.epamlab.factories.DecimalResultFactory;
import by.gsu.epamlab.factories.ResultFactory;
import by.gsu.epamlab.parsers.XMLParser;
import exceptions.ParseException;

public class Task2Runner {
    public static final String SRC_TASK2_XML = "src/task2.xml";

    public static void main(String[] args) {
        System.out.println("Task 2 (xml)\r\n");

        try {
            ResultFactory resultFactory = new DecimalResultFactory();
            XMLParser parser = new XMLParser(resultFactory, SRC_TASK2_XML);
            ResultDaoImpl resultDao = new ResultDaoImpl(resultFactory);
            RunnerLogic.doSomething(parser, resultDao);
        } catch (ParseException e) {
            System.err.println(e);
        }
    }
}