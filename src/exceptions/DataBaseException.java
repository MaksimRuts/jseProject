package exceptions;

public class DataBaseException extends RuntimeException {
    private String message;

    public DataBaseException(Throwable cause) {
        super(cause);
    }

    public DataBaseException(String message, Throwable cause) {
        super(cause);
        this.message = message;
    }

    @Override
    public String toString() {
        return "[DataBase exception \'" + message + "\']" + ExceptionsConstants.SEPARATOR + super.getCause();
    }
}
