package by.gsu.epamlab.parsers;

import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.factories.ResultFactory;
import exceptions.ParseException;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.Scanner;

public class CSVParser extends AbstractParser {
    private final String FILEPATCH;
    private static final String FILE_SEPARATOR = ";";

    private enum CSVFields {LOGIN, TEST, DATE, MARK}

    private Scanner scanner = null;

    public CSVParser(ResultFactory factory, String filePatch) {
        super(factory);
        this.FILEPATCH = filePatch;
        try {
            scanner = new Scanner(new File(FILEPATCH));
        } catch (IOException e) {
            throw new ParseException(FILE_NOT_FOUND, e);
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
    public Result nextResult() {
        if (scanner != null) {
            String resultStr = scanner.nextLine();
            String[] resultsArray = resultStr.split(FILE_SEPARATOR);
            Result result = createInstance();

            try {
                result.setLogin(resultsArray[CSVFields.LOGIN.ordinal()]);
                result.setTest(resultsArray[CSVFields.TEST.ordinal()]);
                result.setDate(Date.valueOf(resultsArray[CSVFields.DATE.ordinal()]));
                result.setMark(resultsArray[CSVFields.MARK.ordinal()]);
                return result;
            } catch (IllegalArgumentException e) {
                throw new ParseException(resultStr, e);
            }
        }
        return null;
    }

    @Override
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
