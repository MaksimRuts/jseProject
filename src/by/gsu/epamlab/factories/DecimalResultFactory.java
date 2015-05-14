package by.gsu.epamlab.factories;

import by.gsu.epamlab.beans.DecimalResult;
import by.gsu.epamlab.beans.Result;

public class DecimalResultFactory extends ResultFactory {
    @Override
    public Result newInstance() {
        return new DecimalResult();
    }
}
