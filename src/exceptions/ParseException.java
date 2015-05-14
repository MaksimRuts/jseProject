package exceptions;

public class ParseException extends IllegalArgumentException {
    private String causeLine;

    public ParseException(Throwable cause) {
        super(cause);
    }

    public ParseException(String s, String causeLine) {
        super(s);
        this.causeLine = causeLine;
    }

    public ParseException(Throwable cause, String causeLine) {
        super(cause);
        this.causeLine = causeLine;
    }

    public ParseException(String message, Throwable cause) {
        super(cause);
        this.causeLine = message;
    }

    @Override
    public String toString() {
        return "[File parsing error \'" + causeLine + "\']" + ExceptionsConstants.SEPARATOR + super.getCause();
    }
}
