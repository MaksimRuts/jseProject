package by.gsu.epamlab.beans;

import java.sql.Date;

public class CSVInt20Result extends AbstractResult {
//    public CSVInt20Result() {
//    }
//
//    public CSVInt20Result(String login, String test, Date date, int mark) {
//        super(login, test, date, mark);
//    }
//
//    public CSVInt20Result(String login, String test, Date date, String mark) {
//        super(login, test, date, mark);
//    }

    @Override
    protected int markToInt(String mark) {
        return (int)(Double.parseDouble(mark) * 5);
    }

    @Override
    protected String markToString(int mark) {
//        double a = (mark / 5);
//        double b = (((mark % 5) > 0.5) ? 0 : 0.5);
//
//        return String.valueOf(a + b);

        return String.valueOf((mark / 5) + (((mark % 5) > 0.5) ? 0 : 0.5));
    }
}
