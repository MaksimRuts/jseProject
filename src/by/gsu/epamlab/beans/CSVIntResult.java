package by.gsu.epamlab.beans;

import java.sql.Date;

public class CSVIntResult extends AbstractResult {

//    public CSVIntResult() {
//    }
//
//    public CSVIntResult(String login, String test, Date date, int mark) {
//        super(login, test, date, mark);
//    }
//
//    public CSVIntResult(String login, String test, Date date, String mark) {
//        super(login, test, date, mark);
//    }

    @Override
    protected int markToInt(String mark) {
        return (int)(Double.parseDouble(mark) * 10);
    }

    @Override
    protected String markToString(int mark) {
        return String.valueOf(mark / 10);
    }
}
