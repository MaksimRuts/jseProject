package by.gsu.epamlab.parsers;

import by.gsu.epamlab.beans.AbstractResult;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.util.Scanner;

public class CSVParser extends AbstractParser {
    private final String FILEPATCH;
    private static final String FILE_SEPARATOR = ";";

    private enum CSVFields {LOGIN, TEST, DATE, MARK}

    private Scanner scanner = null;

    public <T extends AbstractResult> CSVParser(T result, String filePatch) {
        super(result);
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
    public AbstractResult getResult() {
        if (scanner != null) {
            String resultStr = scanner.nextLine();
            String[] resultsArray = resultStr.split(FILE_SEPARATOR);
            AbstractResult result = getInstance();

            result.setLogin(resultsArray[CSVFields.LOGIN.ordinal()]);
            result.setTest(resultsArray[CSVFields.TEST.ordinal()]);
            result.setDate(Date.valueOf(resultsArray[CSVFields.DATE.ordinal()]));
            result.setMark(resultsArray[CSVFields.MARK.ordinal()]);
            return result;
        }
        // fixme maybe return new Result()??
        return null;
    }
}
