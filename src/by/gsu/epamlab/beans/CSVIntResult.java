package by.gsu.epamlab.beans;

public class CSVIntResult extends AbstractResult {

    @Override
    protected int markToInt(String mark) {
        return Integer.parseInt(mark);
    }

    @Override
    protected String markToString(int mark) {
        return String.valueOf(mark);
    }
}
