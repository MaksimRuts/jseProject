package by.gsu.epamlab.parsers;

import by.gsu.epamlab.beans.Result;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.util.Scanner;

public class CSVParser extends AbstractParser{
    private final String FILEPATCH;
    private static final String FILE_SEPARATOR = ";";
    private enum CSVFields {LOGIN, TEST, DATE, MARK};
    Scanner scanner = null;

    public CSVParser(String filePatch) {
        this.FILEPATCH = filePatch;
        try {
            scanner = new Scanner(new File(FILEPATCH));
        } catch (FileNotFoundException e) {
            System.err.println("File didn't found");
        }

    }

    @Override
    public boolean hasNextResult() {
        if (scanner != null) {
            return scanner.hasNextLine();
        }
        return false;
    }

    @Override
    public Result getResult() {
        if (scanner != null) {
            String resultStr = scanner.nextLine();
            String[] resultsArray = resultStr.split(FILE_SEPARATOR);
            Result result = new Result(resultsArray[CSVFields.LOGIN.ordinal()],
                    resultsArray[CSVFields.TEST.ordinal()],
                    Date.valueOf(resultsArray[CSVFields.DATE.ordinal()]),
                    Integer.parseInt(resultsArray[CSVFields.MARK.ordinal()]));
            return result;
        }
        // fixme maybe return new Result()??
        return null;
    }
}
