package by.gsu.epamlab.factories;

import by.gsu.epamlab.beans.HalfResult;
import by.gsu.epamlab.beans.Result;

public class HalfResultFactory extends ResultFactory {
    @Override
    public Result newInstance() {
        return new HalfResult();
    }
}
