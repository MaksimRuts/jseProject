package by.gsu.epamlab.beans;

import java.sql.Date;

public class Result {
    private String login;
    private String test;
    private Date date;
    private int mark;

    public Result() {
    }

    public Result(String login, String test, Date date, int mark) {
        this.login = login;
        this.test = test;
        this.date = date;
        this.mark = mark;
    }

    public Result(String login, String test, Date date, String mark) {
        this.login = login;
        this.test = test;
        this.date = date;
        this.mark = markToInt(mark);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public void setMark(String mark) {
        this.mark = markToInt(mark);
    }

    protected int markToInt(String mark) {
        return Integer.parseInt(mark) * 10;
    }

    protected String markToString(int mark) {
        return String.valueOf(mark / 10);
    }

    @Override
    public String toString() {
        return login + ';' + test + ';' + date + ';' + markToString(mark);
    }
}
