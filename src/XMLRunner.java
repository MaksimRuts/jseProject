import by.gsu.epamlab.beans.AbstractResult;
import by.gsu.epamlab.beans.XMLResult;
import by.gsu.epamlab.daoImpl.ResultDaoImpl;
import by.gsu.epamlab.database.managment.BaseManager;
import by.gsu.epamlab.parsers.XMLParser;

public class XMLRunner {
    public static final String SRC_TASK2_XML = "src/task2.xml";

    public static void main(String[] args) {
        System.out.println("Task 2 (xml)\r\n");

        BaseManager.clearDatabase();
        AbstractResult resultInstance = new XMLResult();

        XMLParser parser = new XMLParser(resultInstance, SRC_TASK2_XML);
        ResultDaoImpl resultDao = new ResultDaoImpl(resultInstance);

        RunnerLogic.doSomething(parser, resultDao);
    }
}