package by.gsu.epamlab.beans;

public class XMLResult extends AbstractResult {

    @Override
    protected String markToString(int mark) {
        return (mark / 10) + "." + (mark % 10);
    }

    @Override
        protected int markToInt(String mark) {
        return (int)(Double.parseDouble(mark) * 10);
    }
}
