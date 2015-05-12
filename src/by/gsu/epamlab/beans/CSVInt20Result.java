package by.gsu.epamlab.beans;

public class CSVInt20Result extends AbstractResult {

    @Override
    protected int markToInt(String mark) {
        return (int)(Double.parseDouble(mark) * 5);
    }

    @Override
    protected String markToString(int mark) {
        return String.valueOf((mark / 5) + (((mark % 5) > 0.5) ? 0 : 0.5));
    }
}
