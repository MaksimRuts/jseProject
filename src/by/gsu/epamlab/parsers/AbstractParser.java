package by.gsu.epamlab.parsers;

import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.factories.ResultFactory;

public abstract class AbstractParser {
    private final ResultFactory factory;
    public static final String FILE_NOT_FOUND = "File didn't found";

    public AbstractParser(ResultFactory factory) {
        this.factory = factory;
    }

    public abstract boolean hasNextResult();

    public abstract Result nextResult();

    public abstract void close();

    protected Result createInstance() {
        return factory.newInstance();
    }
}
