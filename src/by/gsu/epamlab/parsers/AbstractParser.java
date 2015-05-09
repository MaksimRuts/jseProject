package by.gsu.epamlab.parsers;

import by.gsu.epamlab.beans.AbstractResult;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class AbstractParser {
    public abstract boolean hasNextResult();

    public abstract AbstractResult getResult();

    private final AbstractResult result;

    public <T extends AbstractResult> AbstractParser(T result) {
        this.result = result;
    }

    protected AbstractResult getInstance() {
        try {

            Constructor constructor = result.getClass().getConstructor(null);
            return (AbstractResult) constructor.newInstance(null);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
