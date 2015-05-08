package by.gsu.epamlab.parsers;

import by.gsu.epamlab.beans.Result;

public abstract class AbstractParser {
    public abstract boolean hasNextResult();
    public abstract Result getResult();
}
