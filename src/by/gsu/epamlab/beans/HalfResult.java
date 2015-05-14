package by.gsu.epamlab.beans;

public class HalfResult extends Result {

    @Override
    protected int markToInt(String mark) {
        return (int) (Double.parseDouble(mark) * 10);
    }

    @Override
    protected String markToString(int mark) {
        return String.valueOf((mark / 10) + (((mark % 10) > 0.5) ? 0 : 0.5));
    }
}
